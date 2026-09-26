package com.evplatform.station_service.service;

import com.evplatform.station_service.dto.ChargerResponse;
import com.evplatform.station_service.dto.CreateChargerRequest;
import com.evplatform.station_service.dto.UpdateChargerRequest;

import java.util.List;
import java.util.UUID;

public interface ChargerService {

    ChargerResponse createCharger(
            UUID stationId,
            CreateChargerRequest request
    );

    List<ChargerResponse> getStationChargers(UUID stationId);

    ChargerResponse getCharger(UUID chargerId);

    ChargerResponse updateCharger(
            UUID chargerId,
            UpdateChargerRequest request
    );

    void deleteCharger(UUID chargerId);

    ChargerResponse getStationCharger(UUID stationId, UUID chargerId);
}