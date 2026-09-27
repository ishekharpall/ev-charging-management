package com.evplatform.payment_service.repository;

import com.evplatform.payment_service.entity.Payment;
import com.evplatform.payment_service.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository
        extends JpaRepository<Payment, UUID> {

    Optional<Payment> findByChargingSessionId(
            UUID chargingSessionId
    );

    List<Payment> findByUserId(UUID userId);

    List<Payment> findByStatus(PaymentStatus status);
}