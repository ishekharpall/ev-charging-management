package com.evplatform.station_service.controller;

import com.evplatform.station_service.dto.ChargerResponse;
import com.evplatform.station_service.dto.CreateChargerRequest;
import com.evplatform.station_service.dto.UpdateChargerRequest;
import com.evplatform.station_service.service.ChargerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChargerController {

    private final ChargerService chargerService;

    @PostMapping("/stations/{stationId}/chargers")
    public ResponseEntity<ChargerResponse> createCharger(
            @PathVariable UUID stationId,
            @Valid @RequestBody CreateChargerRequest request
    ) {

        ChargerResponse response =
                chargerService.createCharger(stationId, request);

        URI location = URI.create(
                "/api/v1/chargers/" + response.getId()
        );

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @GetMapping("/stations/{stationId}/chargers")
    public ResponseEntity<List<ChargerResponse>> getStationChargers(
            @PathVariable UUID stationId
    ) {

        return ResponseEntity.ok(
                chargerService.getStationChargers(stationId)
        );
    }

    @GetMapping("/chargers/{chargerId}")
    public ResponseEntity<ChargerResponse> getCharger(
            @PathVariable UUID chargerId
    ) {

        return ResponseEntity.ok(
                chargerService.getCharger(chargerId)
        );
    }

    @PutMapping("/chargers/{chargerId}")
    public ResponseEntity<ChargerResponse> updateCharger(
            @PathVariable UUID chargerId,
            @Valid @RequestBody UpdateChargerRequest request
    ) {

        return ResponseEntity.ok(
                chargerService.updateCharger(chargerId, request)
        );
    }

    @DeleteMapping("/chargers/{chargerId}")
    public ResponseEntity<Void> deleteCharger(
            @PathVariable UUID chargerId
    ) {

        chargerService.deleteCharger(chargerId);

        return ResponseEntity.noContent().build();
    }
}