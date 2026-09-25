package com.evplatform.station_service.entity;

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
        name = "chargers",
        indexes = {
                @Index(name = "idx_charger_station_id", columnList = "station_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Charger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    @Column(name = "charger_number", nullable = false)
    private String chargerNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "connector_type", nullable = false)
    private ConnectorType connectorType;

    @Column(name = "power_rating_kw", nullable = false, precision = 6, scale = 2)
    private BigDecimal powerRatingKw;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargerStatus status;

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
            status = ChargerStatus.AVAILABLE;
        }
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = LocalDateTime.now();
    }
}