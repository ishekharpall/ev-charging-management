package com.evplatform.payment_service.entity;

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
        name = "payments",
        indexes = {
                @Index(
                        name = "idx_payment_charging_session_id",
                        columnList = "charging_session_id"
                ),
                @Index(
                        name = "idx_payment_user_id",
                        columnList = "user_id"
                ),
                @Index(
                        name = "idx_payment_status",
                        columnList = "status"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "charging_session_id",
            nullable = false,
            unique = true
    )
    private UUID chargingSessionId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(
            name = "energy_consumed_kwh",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal energyConsumedKwh;

    @Column(
            name = "price_per_kwh",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal pricePerKwh;

    @Column(
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

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
            status = PaymentStatus.PENDING;
        }

        if (currency == null) {
            currency = "INR";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}