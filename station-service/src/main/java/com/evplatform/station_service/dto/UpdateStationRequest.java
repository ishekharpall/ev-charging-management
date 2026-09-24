package com.evplatform.station_service.dto;


import com.evplatform.station_service.entity.StationStatus;
import jakarta.validation.constraints.*;

public record UpdateStationRequest(

        @NotBlank(message = "Station name is required")
        @Size(max = 150)
        String name,

        @Size(max = 500)
        String description,

        @NotNull
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        Double latitude,

        @NotNull
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        Double longitude,

        @NotBlank
        @Size(max = 500)
        String address,

        @NotBlank
        @Size(max = 100)
        String city,

        @Size(max = 100)
        String state,

        @Size(max = 100)
        String country,

        @Size(max = 20)
        String postalCode,

        @NotNull
        StationStatus status
) {
}