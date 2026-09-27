package com.evplatform.charging_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StopChargingRequest {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal energyConsumedKwh;
}