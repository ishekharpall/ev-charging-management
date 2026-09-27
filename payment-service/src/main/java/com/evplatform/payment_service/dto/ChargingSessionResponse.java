package com.evplatform.payment_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChargingSessionResponse {

    private UUID id;
    private UUID bookingId;
    private UUID userId;
    private UUID vehicleId;
    private UUID stationId;
    private UUID chargerId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private BigDecimal energyConsumedKwh;
    private ChargingSessionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}