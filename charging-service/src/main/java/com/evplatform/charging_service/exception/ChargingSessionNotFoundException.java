package com.evplatform.charging_service.exception;

import java.util.UUID;

public class ChargingSessionNotFoundException extends RuntimeException {

    public ChargingSessionNotFoundException(UUID sessionId) {
        super("Charging session not found with id: " + sessionId);
    }
}