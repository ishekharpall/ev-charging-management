package com.evplatform.charging_service.repository;

import com.evplatform.charging_service.entity.ChargingSession;
import com.evplatform.charging_service.entity.ChargingSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChargingSessionRepository
        extends JpaRepository<ChargingSession, UUID> {

    Optional<ChargingSession> findByBookingId(UUID bookingId);

    List<ChargingSession> findByUserId(UUID userId);

    List<ChargingSession> findByChargerId(UUID chargerId);

    List<ChargingSession> findByStatus(
            ChargingSessionStatus status
    );
}