package com.evplatform.charging_service.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ChargingSessionCompletedEvent(
        UUID chargingSessionId,
        UUID bookingId,
        UUID userId,
        UUID vehicleId,
        UUID stationId,
        UUID chargerId,
        BigDecimal energyConsumedKwh,
        LocalDateTime completedAt
) {
}