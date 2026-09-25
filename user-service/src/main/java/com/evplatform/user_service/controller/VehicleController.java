package com.evplatform.user_service.controller;

import com.evplatform.user_service.dto.CreateVehicleRequest;
import com.evplatform.user_service.dto.UpdateVehicleRequest;
import com.evplatform.user_service.dto.VehicleResponse;
import com.evplatform.user_service.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/users/{userId}/vehicles")
    public ResponseEntity<VehicleResponse> createVehicle(
            @PathVariable UUID userId,
            @Valid @RequestBody CreateVehicleRequest request
    ) {

        VehicleResponse response =
                vehicleService.createVehicle(
                        userId,
                        request
                );

        URI location = URI.create(
                "/api/v1/vehicles/" + response.getId()
        );

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @GetMapping("/users/{userId}/vehicles")
    public ResponseEntity<List<VehicleResponse>>
    getUserVehicles(
            @PathVariable UUID userId
    ) {

        return ResponseEntity.ok(
                vehicleService.getUserVehicles(userId)
        );
    }

    @GetMapping("/vehicles/{vehicleId}")
    public ResponseEntity<VehicleResponse> getVehicle(
            @PathVariable UUID vehicleId
    ) {

        return ResponseEntity.ok(
                vehicleService.getVehicle(vehicleId)
        );
    }

    @PutMapping("/vehicles/{vehicleId}")
    public ResponseEntity<VehicleResponse> updateVehicle(
            @PathVariable UUID vehicleId,
            @Valid @RequestBody UpdateVehicleRequest request
    ) {

        return ResponseEntity.ok(
                vehicleService.updateVehicle(
                        vehicleId,
                        request
                )
        );
    }

    @DeleteMapping("/vehicles/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable UUID vehicleId
    ) {

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.noContent().build();
    }
}