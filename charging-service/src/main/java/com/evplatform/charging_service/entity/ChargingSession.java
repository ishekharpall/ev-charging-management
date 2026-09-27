package com.evplatform.charging_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "charging_sessions",
        indexes = {
                @Index(name = "idx_session_booking_id", columnList = "booking_id"),
                @Index(name = "idx_session_user_id", columnList = "user_id"),
                @Index(name = "idx_session_vehicle_id", columnList = "vehicle_id"),
                @Index(name = "idx_session_charger_id", columnList = "charger_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "booking_id", nullable = false, unique = true)
    private UUID bookingId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "vehicle_id", nullable = false)
    private UUID vehicleId;

    @Column(name = "station_id", nullable = false)
    private UUID stationId;

    @Column(name = "charger_id", nullable = false)
    private UUID chargerId;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "energy_consumed_kwh", precision = 10, scale = 2)
    private BigDecimal energyConsumedKwh;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargingSessionStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;

        if (status == null) {
            status = ChargingSessionStatus.STARTED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}