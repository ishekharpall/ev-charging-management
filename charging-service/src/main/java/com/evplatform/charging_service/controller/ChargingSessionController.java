package com.evplatform.charging_service.controller;

import com.evplatform.charging_service.dto.StartChargingRequest;
import com.evplatform.charging_service.dto.StopChargingRequest;
import com.evplatform.charging_service.entity.ChargingSession;
import com.evplatform.charging_service.service.ChargingSessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ChargingSession> startCharging(
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
    public ResponseEntity<ChargingSession> getChargingSession(
            @PathVariable UUID sessionId
    ) {
        return ResponseEntity.ok(
                chargingSessionService.getChargingSession(
                        sessionId
                )
        );
    }

    @PutMapping("/{sessionId}/stop")
    public ResponseEntity<ChargingSession> stopCharging(
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
}