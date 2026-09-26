package com.evplatform.booking_service.client;

import com.evplatform.booking_service.dto.VehicleServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "user-service",
        url = "${services.user-service.url}"
)
public interface UserServiceClient {

    @GetMapping("/api/v1/users/{userId}/vehicles/{vehicleId}")
    VehicleServiceResponse getUserVehicle(
            @PathVariable UUID userId,
            @PathVariable UUID vehicleId
    );
}