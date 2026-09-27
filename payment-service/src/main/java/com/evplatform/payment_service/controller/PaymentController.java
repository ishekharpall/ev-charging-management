package com.evplatform.payment_service.controller;

import com.evplatform.payment_service.dto.CreatePaymentRequest;
import com.evplatform.payment_service.dto.PaymentResponse;
import com.evplatform.payment_service.entity.PaymentStatus;
import com.evplatform.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        paymentService.createPayment(
                                request
                        )
                );
    }

    @PutMapping("/{paymentId}/process")
    public ResponseEntity<PaymentResponse> processPayment(
            @PathVariable UUID paymentId,
            @RequestParam boolean successful
    ) {
        return ResponseEntity.ok(
                paymentService.processPayment(paymentId, successful)
        );
    }
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable UUID paymentId
    ) {

        return ResponseEntity.ok(
                paymentService.getPayment(paymentId)
        );
    }

    @GetMapping("/charging-session/{chargingSessionId}")
    public ResponseEntity<PaymentResponse>
    getPaymentByChargingSession(
            @PathVariable UUID chargingSessionId
    ) {

        return ResponseEntity.ok(
                paymentService.getPaymentByChargingSession(
                        chargingSessionId
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponse>>
    getUserPayments(
            @PathVariable UUID userId
    ) {

        return ResponseEntity.ok(
                paymentService.getUserPayments(userId)
        );
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>>
    getPaymentsByStatus(
            @RequestParam PaymentStatus status
    ) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByStatus(status)
        );
    }
}