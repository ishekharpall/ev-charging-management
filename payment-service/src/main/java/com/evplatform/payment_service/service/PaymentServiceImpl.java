package com.evplatform.payment_service.service;

import com.evplatform.payment_service.client.ChargingServiceClient;
import com.evplatform.payment_service.dto.ChargingSessionResponse;
import com.evplatform.payment_service.dto.ChargingSessionStatus;
import com.evplatform.payment_service.dto.CreatePaymentRequest;
import com.evplatform.payment_service.dto.PaymentResponse;
import com.evplatform.payment_service.entity.Payment;
import com.evplatform.payment_service.entity.PaymentStatus;
import com.evplatform.payment_service.exception.PaymentNotFoundException;
import com.evplatform.payment_service.mapper.PaymentMapper;
import com.evplatform.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl
        implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ChargingServiceClient chargingServiceClient;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper,
            ChargingServiceClient chargingServiceClient
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.chargingServiceClient = chargingServiceClient;
    }

    @Override
    public PaymentResponse createPayment(
            CreatePaymentRequest request
    ) {

        ChargingSessionResponse session =
                chargingServiceClient.getChargingSession(
                        request.getChargingSessionId()
                );

        if (session.getStatus()
                != ChargingSessionStatus.COMPLETED) {

            throw new IllegalStateException(
                    "Payment can only be created for a completed charging session"
            );
        }

        if (paymentRepository
                .findByChargingSessionId(
                        request.getChargingSessionId()
                )
                .isPresent()) {

            throw new IllegalStateException(
                    "Payment already exists for this charging session"
            );
        }

        BigDecimal amount =
                session.getEnergyConsumedKwh()
                        .multiply(request.getPricePerKwh());

        Payment payment = new Payment();

        payment.setChargingSessionId(
                session.getId()
        );

        payment.setUserId(
                session.getUserId()
        );

        payment.setEnergyConsumedKwh(
                session.getEnergyConsumedKwh()
        );

        payment.setPricePerKwh(
                request.getPricePerKwh()
        );

        payment.setAmount(amount);

        payment.setCurrency("INR");

        payment.setStatus(PaymentStatus.PENDING);

        Payment savedPayment =
                paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }


    @Override
    public PaymentResponse processPayment(
            UUID paymentId,
            boolean successful
    ) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        if (payment.getStatus() != PaymentStatus.PENDING) {
            throw new IllegalStateException(
                    "Only a pending payment can be processed"
            );
        }

        if (successful) {
            payment.setStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }


    @Override
    public PaymentResponse getPayment(
            UUID paymentId
    ) {

        Payment payment =
                paymentRepository
                        .findById(paymentId)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        paymentId
                                )
                        );

        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentByChargingSession(
            UUID chargingSessionId
    ) {

        Payment payment =
                paymentRepository
                        .findByChargingSessionId(
                                chargingSessionId
                        )
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "Payment not found for charging session"
                                )
                        );

        return paymentMapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getUserPayments(
            UUID userId
    ) {

        return paymentRepository
                .findByUserId(userId)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public List<PaymentResponse> getPaymentsByStatus(
            PaymentStatus status
    ) {

        return paymentRepository
                .findByStatus(status)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }


}