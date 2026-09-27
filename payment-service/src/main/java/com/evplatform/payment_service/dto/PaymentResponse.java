package com.evplatform.payment_service.dto;

import com.evplatform.payment_service.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PaymentResponse {

    private UUID id;
    private UUID chargingSessionId;
    private UUID userId;
    private BigDecimal energyConsumedKwh;
    private BigDecimal pricePerKwh;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}