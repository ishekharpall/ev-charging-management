package com.evplatform.station_service.dto;

import com.evplatform.station_service.entity.ChargerStatus;
import com.evplatform.station_service.entity.ConnectorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChargerResponse {

    private UUID id;

    private UUID stationId;

    private String chargerNumber;

    private ConnectorType connectorType;

    private BigDecimal powerRatingKw;

    private ChargerStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}