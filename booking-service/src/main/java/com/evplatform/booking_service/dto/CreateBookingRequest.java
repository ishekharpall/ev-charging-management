package com.evplatform.booking_service.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateBookingRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID vehicleId;

    @NotNull
    private UUID stationId;

    @NotNull
    private UUID chargerId;

    @NotNull
    @Future
    private LocalDateTime startTime;

    @NotNull
    @Future
    private LocalDateTime endTime;
}