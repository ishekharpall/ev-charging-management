package com.evplatform.station_service.service;

import com.evplatform.station_service.dto.CreateStationRequest;
import com.evplatform.station_service.dto.NearbyStationResponse;
import com.evplatform.station_service.dto.StationResponse;
import com.evplatform.station_service.dto.UpdateStationRequest;

import java.util.List;
import java.util.UUID;

public interface StationService {

    StationResponse createStation(CreateStationRequest request);

    StationResponse getStation(UUID id);

    List<StationResponse> getAllStations();

    StationResponse updateStation(
            UUID id,
            UpdateStationRequest request
    );

    void deleteStation(UUID id);

    List<NearbyStationResponse> findNearbyStations(
            double latitude,
            double longitude,
            double radiusKm
    );

}