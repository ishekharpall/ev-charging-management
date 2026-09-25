package com.evplatform.user_service.mapper;

import com.evplatform.user_service.dto.CreateVehicleRequest;
import com.evplatform.user_service.dto.UpdateVehicleRequest;
import com.evplatform.user_service.dto.VehicleResponse;
import com.evplatform.user_service.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VehicleMapper {

    public Vehicle toEntity(
            CreateVehicleRequest request,
            UUID userId
    ) {

        Vehicle vehicle = new Vehicle();

        vehicle.setUserId(userId);
        vehicle.setRegistrationNumber(
                request.getRegistrationNumber()
        );
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setBatteryCapacity(
                request.getBatteryCapacity()
        );
        vehicle.setConnectorType(
                request.getConnectorType()
        );

        return vehicle;
    }

    public void updateEntity(
            Vehicle vehicle,
            UpdateVehicleRequest request
    ) {

        vehicle.setRegistrationNumber(
                request.getRegistrationNumber()
        );
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setBatteryCapacity(
                request.getBatteryCapacity()
        );
        vehicle.setConnectorType(
                request.getConnectorType()
        );
    }

    public VehicleResponse toResponse(Vehicle vehicle) {

        VehicleResponse response = new VehicleResponse();

        response.setId(vehicle.getId());
        response.setUserId(vehicle.getUserId());
        response.setRegistrationNumber(
                vehicle.getRegistrationNumber()
        );
        response.setBrand(vehicle.getBrand());
        response.setModel(vehicle.getModel());
        response.setBatteryCapacity(
                vehicle.getBatteryCapacity()
        );
        response.setConnectorType(
                vehicle.getConnectorType()
        );
        response.setCreatedAt(vehicle.getCreatedAt());
        response.setUpdatedAt(vehicle.getUpdatedAt());

        return response;
    }
}