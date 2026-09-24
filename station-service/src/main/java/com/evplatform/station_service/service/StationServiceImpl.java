package com.evplatform.station_service.service;

import com.evplatform.station_service.dto.CreateStationRequest;
import com.evplatform.station_service.dto.StationResponse;
import com.evplatform.station_service.dto.UpdateStationRequest;
import com.evplatform.station_service.entity.Station;
import com.evplatform.station_service.exception.StationNotFoundException;
import com.evplatform.station_service.mapper.StationMapper;
import com.evplatform.station_service.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationMapper stationMapper;

    @Override
    @Transactional
    public StationResponse createStation(CreateStationRequest request) {
        Station station = stationMapper.toEntity(request);

        Station savedStation = stationRepository.save(station);

        return stationMapper.toResponse(savedStation);
    }

    @Override
    public StationResponse getStation(UUID id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));

        return stationMapper.toResponse(station);
    }

    @Override
    public List<StationResponse> getAllStations() {
        return stationRepository.findAll()
                .stream()
                .map(stationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public StationResponse updateStation(UUID id, UpdateStationRequest request) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));

        stationMapper.updateEntity(station, request);

        Station updatedStation = stationRepository.save(station);

        return stationMapper.toResponse(updatedStation);
    }

    @Override
    @Transactional
    public void deleteStation(UUID id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new StationNotFoundException(id));

        stationRepository.delete(station);
    }
}