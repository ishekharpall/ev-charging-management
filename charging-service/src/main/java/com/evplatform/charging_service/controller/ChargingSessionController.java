package com.evplatform.charging_service.controller;

import com.evplatform.charging_service.dto.ChargingSessionResponse;
import com.evplatform.charging_service.dto.StartChargingRequest;
import com.evplatform.charging_service.dto.StopChargingRequest;
import com.evplatform.charging_service.entity.ChargingSessionStatus;
import com.evplatform.charging_service.service.ChargingSessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/charging-sessions")
public class ChargingSessionController {

    private final ChargingSessionService chargingSessionService;

    public ChargingSessionController(
            ChargingSessionService chargingSessionService
    ) {
        this.chargingSessionService = chargingSessionService;
    }

    @PostMapping("/start")
    public ResponseEntity<ChargingSessionResponse> startCharging(
            @Valid @RequestBody StartChargingRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        chargingSessionService.startCharging(
                                request
                        )
                );
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<ChargingSessionResponse> getChargingSession(
            @PathVariable UUID sessionId
    ) {

        return ResponseEntity.ok(
                chargingSessionService.getChargingSession(
                        sessionId
                )
        );
    }

    @PutMapping("/{sessionId}/stop")
    public ResponseEntity<ChargingSessionResponse> stopCharging(
            @PathVariable UUID sessionId,
            @Valid @RequestBody StopChargingRequest request
    ) {

        return ResponseEntity.ok(
                chargingSessionService.stopCharging(
                        sessionId,
                        request
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChargingSessionResponse>> getUserSessions(
            @PathVariable UUID userId
    ) {

        return ResponseEntity.ok(
                chargingSessionService.getUserSessions(
                        userId
                )
        );
    }

    @GetMapping("/charger/{chargerId}")
    public ResponseEntity<List<ChargingSessionResponse>> getChargerSessions(
            @PathVariable UUID chargerId
    ) {

        return ResponseEntity.ok(
                chargingSessionService.getChargerSessions(
                        chargerId
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ChargingSessionResponse>> getSessionsByStatus(
            @RequestParam ChargingSessionStatus status
    ) {

        return ResponseEntity.ok(
                chargingSessionService.getSessionsByStatus(
                        status
                )
        );
    }
}