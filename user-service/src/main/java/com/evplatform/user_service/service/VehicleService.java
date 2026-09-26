package com.evplatform.user_service.service;

import com.evplatform.user_service.dto.CreateVehicleRequest;
import com.evplatform.user_service.dto.UpdateVehicleRequest;
import com.evplatform.user_service.dto.VehicleResponse;

import java.util.List;
import java.util.UUID;

public interface VehicleService {

    VehicleResponse createVehicle(
            UUID userId,
            CreateVehicleRequest request
    );

    List<VehicleResponse> getUserVehicles(UUID userId);

    VehicleResponse getVehicle(UUID vehicleId);

    VehicleResponse updateVehicle(
            UUID vehicleId,
            UpdateVehicleRequest request
    );

    void deleteVehicle(UUID vehicleId);

    VehicleResponse getUserVehicle(UUID userId, UUID vehicleId);
}