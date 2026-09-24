package com.evplatform.station_service.exception;

import java.util.UUID;

public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException(UUID id) {

        super("Station not found with id: " + id);
    }
}