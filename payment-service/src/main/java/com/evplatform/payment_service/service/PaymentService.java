package com.evplatform.payment_service.service;

import com.evplatform.payment_service.dto.CreatePaymentRequest;
import com.evplatform.payment_service.dto.PaymentResponse;
import com.evplatform.payment_service.entity.PaymentStatus;

import java.util.List;
import java.util.UUID;

public interface PaymentService {

    PaymentResponse createPayment(
            CreatePaymentRequest request
    );

    PaymentResponse processPayment(UUID paymentId, boolean successful);

    PaymentResponse getPayment(
            UUID paymentId
    );

    PaymentResponse getPaymentByChargingSession(
            UUID chargingSessionId
    );

    List<PaymentResponse> getUserPayments(
            UUID userId
    );

    List<PaymentResponse> getPaymentsByStatus(
            PaymentStatus status
    );


}