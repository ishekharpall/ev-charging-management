package com.evplatform.user_service.dto;

import com.evplatform.user_service.entity.ConnectorType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class VehicleResponse {

    private UUID id;

    private UUID userId;

    private String registrationNumber;

    private String brand;

    private String model;

    private BigDecimal batteryCapacity;

    private ConnectorType connectorType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}