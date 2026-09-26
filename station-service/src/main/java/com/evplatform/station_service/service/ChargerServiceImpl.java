package com.evplatform.station_service.service;

import com.evplatform.station_service.dto.ChargerResponse;
import com.evplatform.station_service.dto.CreateChargerRequest;
import com.evplatform.station_service.dto.UpdateChargerRequest;
import com.evplatform.station_service.entity.Charger;
import com.evplatform.station_service.entity.Station;
import com.evplatform.station_service.exception.ChargerNotFoundException;
import com.evplatform.station_service.mapper.ChargerMapper;
import com.evplatform.station_service.repository.ChargerRepository;
import com.evplatform.station_service.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChargerServiceImpl implements ChargerService {

    private final ChargerRepository chargerRepository;
    private final StationRepository stationRepository;
    private final ChargerMapper chargerMapper;

    @Override
    @Transactional
    public ChargerResponse createCharger(
            UUID stationId,
            CreateChargerRequest request
    ) {

        Station station = stationRepository.findById(stationId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Station not found with id: " + stationId
                        )
                );

        if (chargerRepository.existsByStationIdAndChargerNumberIgnoreCase(
                stationId,
                request.getChargerNumber())) {

            throw new IllegalArgumentException(
                    "Charger number already exists for this station"
            );
        }

        Charger charger = chargerMapper.toEntity(request, station);

        Charger savedCharger = chargerRepository.save(charger);

        return chargerMapper.toResponse(savedCharger);
    }

    @Override
    public List<ChargerResponse> getStationChargers(UUID stationId) {

        return chargerRepository.findByStationId(stationId)
                .stream()
                .map(chargerMapper::toResponse)
                .toList();
    }

    @Override
    public ChargerResponse getCharger(UUID chargerId) {

        Charger charger = chargerRepository.findById(chargerId)
                .orElseThrow(() ->
                        new ChargerNotFoundException(chargerId)
                );

        return chargerMapper.toResponse(charger);
    }

    @Override
    @Transactional
    public ChargerResponse updateCharger(
            UUID chargerId,
            UpdateChargerRequest request
    ) {

        Charger charger = chargerRepository.findById(chargerId)
                .orElseThrow(() ->
                        new ChargerNotFoundException(chargerId)
                );

        chargerMapper.updateEntity(charger, request);

        return chargerMapper.toResponse(charger);
    }

    @Override
    @Transactional
    public void deleteCharger(UUID chargerId) {

        Charger charger = chargerRepository.findById(chargerId)
                .orElseThrow(() ->
                        new ChargerNotFoundException(chargerId)
                );

        chargerRepository.delete(charger);
    }

    @Override
    public ChargerResponse getStationCharger(UUID stationId, UUID chargerId) {

        Charger charger = chargerRepository
                .findById(chargerId)
                .orElseThrow(() -> new ChargerNotFoundException(chargerId));

        if (!charger.getStation().getId().equals(stationId)) {
            throw new ChargerNotFoundException(chargerId);
        }

        return chargerMapper.toResponse(charger);
    }
}