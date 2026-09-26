package com.evplatform.user_service.service;

import com.evplatform.user_service.dto.CreateVehicleRequest;
import com.evplatform.user_service.dto.UpdateVehicleRequest;
import com.evplatform.user_service.dto.VehicleResponse;
import com.evplatform.user_service.entity.Vehicle;
import com.evplatform.user_service.exception.UserNotFoundException;
import com.evplatform.user_service.exception.VehicleNotFoundException;
import com.evplatform.user_service.mapper.VehicleMapper;
import com.evplatform.user_service.repository.UserRepository;
import com.evplatform.user_service.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public VehicleResponse createVehicle(
            UUID userId,
            CreateVehicleRequest request
    ) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (vehicleRepository.existsByRegistrationNumberIgnoreCase(
                request.getRegistrationNumber()
        )) {
            throw new IllegalArgumentException(
                    "Vehicle registration number already exists"
            );
        }

        Vehicle vehicle = vehicleMapper.toEntity(
                request,
                userId
        );

        Vehicle savedVehicle =
                vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(savedVehicle);
    }

    @Override
    public List<VehicleResponse> getUserVehicles(
            UUID userId
    ) {

        return vehicleRepository.findByUserId(userId)
                .stream()
                .map(vehicleMapper::toResponse)
                .toList();
    }

    @Override
    public VehicleResponse getVehicle(
            UUID vehicleId
    ) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(
                        () -> new VehicleNotFoundException(vehicleId)
                );

        return vehicleMapper.toResponse(vehicle);
    }

    @Override
    @Transactional
    public VehicleResponse updateVehicle(
            UUID vehicleId,
            UpdateVehicleRequest request
    ) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(
                        () -> new VehicleNotFoundException(vehicleId)
                );

        vehicleMapper.updateEntity(vehicle, request);

        Vehicle updatedVehicle =
                vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(updatedVehicle);
    }

    @Override
    @Transactional
    public void deleteVehicle(
            UUID vehicleId
    ) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(
                        () -> new VehicleNotFoundException(vehicleId)
                );

        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleResponse getUserVehicle(UUID userId, UUID vehicleId) {

        Vehicle vehicle = vehicleRepository
                .findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId));

        if (!vehicle.getUserId().equals(userId)) {
            throw new VehicleNotFoundException(vehicleId);
        }

        return vehicleMapper.toResponse(vehicle);
    }
}