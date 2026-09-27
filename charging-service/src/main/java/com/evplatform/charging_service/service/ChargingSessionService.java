package com.evplatform.charging_service.service;

import com.evplatform.charging_service.dto.ChargingSessionResponse;
import com.evplatform.charging_service.dto.StartChargingRequest;
import com.evplatform.charging_service.dto.StopChargingRequest;
import com.evplatform.charging_service.entity.ChargingSessionStatus;

import java.util.List;
import java.util.UUID;

public interface ChargingSessionService {

    ChargingSessionResponse startCharging(
            StartChargingRequest request
    );

    ChargingSessionResponse getChargingSession(
            UUID sessionId
    );

    ChargingSessionResponse stopCharging(
            UUID sessionId,
            StopChargingRequest request
    );

    List<ChargingSessionResponse> getUserSessions(
            UUID userId
    );

    List<ChargingSessionResponse> getChargerSessions(
            UUID chargerId
    );

    List<ChargingSessionResponse> getSessionsByStatus(
            ChargingSessionStatus status
    );
}