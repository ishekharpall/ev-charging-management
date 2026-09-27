package com.evplatform.charging_service.mapper;

import com.evplatform.charging_service.dto.ChargingSessionResponse;
import com.evplatform.charging_service.entity.ChargingSession;
import org.springframework.stereotype.Component;

@Component
public class ChargingSessionMapper {

    public ChargingSessionResponse toResponse(
            ChargingSession session
    ) {
        return new ChargingSessionResponse(
                session.getId(),
                session.getBookingId(),
                session.getUserId(),
                session.getVehicleId(),
                session.getStationId(),
                session.getChargerId(),
                session.getStartedAt(),
                session.getEndedAt(),
                session.getEnergyConsumedKwh(),
                session.getStatus(),
                session.getCreatedAt(),
                session.getUpdatedAt()
        );
    }
}