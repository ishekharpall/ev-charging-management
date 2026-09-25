package com.evplatform.station_service.repository;

import com.evplatform.station_service.entity.Charger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChargerRepository extends JpaRepository<Charger, UUID> {

    List<Charger> findByStationId(UUID stationId);

    boolean existsByStationIdAndChargerNumberIgnoreCase(
            UUID stationId,
            String chargerNumber
    );
}