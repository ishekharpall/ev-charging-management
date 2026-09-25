package com.evplatform.user_service.repository;

import com.evplatform.user_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository
        extends JpaRepository<Vehicle, UUID> {

    List<Vehicle> findByUserId(UUID userId);

    boolean existsByRegistrationNumberIgnoreCase(
            String registrationNumber
    );
}