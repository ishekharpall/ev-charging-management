package com.evplatform.user_service.entity;

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
        name = "vehicles",
        indexes = {
                @Index(
                        name = "idx_vehicle_user_id",
                        columnList = "user_id"
                ),
                @Index(
                        name = "idx_vehicle_registration",
                        columnList = "registration_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(
            name = "registration_number",
            nullable = false,
            unique = true
    )
    private String registrationNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(
            name = "battery_capacity",
            nullable = false,
            precision = 6,
            scale = 2
    )
    private BigDecimal batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "connector_type",
            nullable = false
    )
    private ConnectorType connectorType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }
}