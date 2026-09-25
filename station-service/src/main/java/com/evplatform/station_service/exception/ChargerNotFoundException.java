package com.evplatform.station_service.exception;

import java.util.UUID;

public class ChargerNotFoundException extends RuntimeException {

    public ChargerNotFoundException(UUID id) {

        super("Charger not found with id: " + id);
    }
}