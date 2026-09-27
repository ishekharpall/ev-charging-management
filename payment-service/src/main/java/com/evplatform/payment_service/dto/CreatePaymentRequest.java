package com.evplatform.payment_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreatePaymentRequest {

    @NotNull
    private UUID chargingSessionId;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal pricePerKwh;
}