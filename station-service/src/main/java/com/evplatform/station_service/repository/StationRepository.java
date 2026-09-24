package com.evplatform.station_service.repository;

import com.evplatform.station_service.entity.Station;
import com.evplatform.station_service.entity.StationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StationRepository extends JpaRepository<Station, UUID> {

    List<Station> findByCityIgnoreCase(String city);

    List<Station> findByStatus(StationStatus status);

    List<Station> findByCityIgnoreCaseAndStatus(
            String city,
            StationStatus status
    );
}