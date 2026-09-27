package com.evplatform.payment_service.mapper;

import com.evplatform.payment_service.dto.PaymentResponse;
import com.evplatform.payment_service.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {

        return new PaymentResponse(
                payment.getId(),
                payment.getChargingSessionId(),
                payment.getUserId(),
                payment.getEnergyConsumedKwh(),
                payment.getPricePerKwh(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}