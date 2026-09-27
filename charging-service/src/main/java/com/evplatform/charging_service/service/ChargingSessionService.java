package com.evplatform.charging_service.service;

import com.evplatform.charging_service.dto.StartChargingRequest;
import com.evplatform.charging_service.dto.StopChargingRequest;
import com.evplatform.charging_service.entity.ChargingSession;

import java.util.UUID;

public interface ChargingSessionService {

    ChargingSession startCharging(
            StartChargingRequest request
    );

    ChargingSession getChargingSession(
            UUID sessionId
    );

    ChargingSession stopCharging(
            UUID sessionId,
            StopChargingRequest request
    );
}