package com.evplatform.station_service.controller;

import com.evplatform.station_service.dto.CreateStationRequest;
import com.evplatform.station_service.dto.StationResponse;
import com.evplatform.station_service.dto.UpdateStationRequest;
import com.evplatform.station_service.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<StationResponse> createStation(
            @Valid @RequestBody CreateStationRequest request
    ) {

        StationResponse response =
                stationService.createStation(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponse> getStation(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                stationService.getStation(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<StationResponse>> getAllStations() {

        return ResponseEntity.ok(
                stationService.getAllStations()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationResponse> updateStation(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateStationRequest request
    ) {

        return ResponseEntity.ok(
                stationService.updateStation(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(
            @PathVariable UUID id
    ) {

        stationService.deleteStation(id);

        return ResponseEntity
                .ok("Station deleted successfully");
    }
}