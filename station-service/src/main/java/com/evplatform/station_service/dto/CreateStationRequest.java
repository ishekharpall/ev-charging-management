package com.evplatform.station_service.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

public record CreateStationRequest(

        @NotBlank(message = "Station name is required")
        @Size(max = 150)
        String name,

        @Size(max = 500)
        String description,

        @NotNull(message = "Latitude is required")
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        Double latitude,

        @NotNull(message = "Longitude is required")
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        Double longitude,

        @NotBlank(message = "Address is required")
        @Size(max = 500)
        String address,

        @NotBlank(message = "City is required")
        @Size(max = 100)
        String city,

        @Size(max = 100)
        String state,

        @Size(max = 100)
        String country,

        @Size(max = 20)
        String postalCode
) {
}