package com.evplatform.booking_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChargerServiceResponse {

    private UUID id;
    private UUID stationId;
    private String chargerNumber;
    private ConnectorType connectorType;
    private BigDecimal powerRatingKw;
    private String status;
}