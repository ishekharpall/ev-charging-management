package com.evplatform.station_service.dto;

import com.evplatform.station_service.entity.StationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StationResponse(

        UUID id,

        String name,

        String description,

        Double latitude,

        Double longitude,

        String address,

        String city,

        String state,

        String country,

        String postalCode,

        StationStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}