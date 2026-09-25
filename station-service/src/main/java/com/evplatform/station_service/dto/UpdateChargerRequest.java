package com.evplatform.station_service.dto;

import com.evplatform.station_service.entity.ChargerStatus;
import com.evplatform.station_service.entity.ConnectorType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateChargerRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String chargerNumber;

    @NotNull
    private ConnectorType connectorType;

    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal powerRatingKw;

    @NotNull
    private ChargerStatus status;
}