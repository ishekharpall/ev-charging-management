package com.evplatform.booking_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class VehicleServiceResponse {

    private UUID id;
    private UUID userId;
    private String registrationNumber;
    private String brand;
    private String model;
    private BigDecimal batteryCapacity;
    private ConnectorType connectorType;
}