package com.evplatform.booking_service.repository;

import com.evplatform.booking_service.entity.Booking;
import com.evplatform.booking_service.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByUserId(UUID userId);

    List<Booking> findByChargerId(UUID chargerId);

    List<Booking> findByStatus(BookingStatus status);

    boolean existsByChargerIdAndStatusInAndStartTimeLessThanAndEndTimeGreaterThan(
            UUID chargerId,
            List<BookingStatus> statuses,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
}