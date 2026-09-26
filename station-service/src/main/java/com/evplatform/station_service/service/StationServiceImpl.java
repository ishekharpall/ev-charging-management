package com.evplatform.station_service.service;

import com.evplatform.station_service.dto.CreateStationRequest;
import com.evplatform.station_service.dto.NearbyStationResponse;
import com.evplatform.station_service.dto.StationResponse;
import com.evplatform.station_service.dto.UpdateStationRequest;
import com.evplatform.station_service.entity.Station;
import com.evplatform.station_service.entity.StationStatus;
import com.evplatform.station_service.exception.StationNotFoundException;
import com.evplatform.station_service.mapper.StationMapper;
import com.evplatform.station_service.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.*;

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

    @Override
    public List<NearbyStationResponse> findNearbyStations(
            double latitude,
            double longitude,
            double radiusKm
    ) {

        return stationRepository.findAll()
                .stream()
                .filter(station -> station.getStatus() == StationStatus.ACTIVE)
                .map(station -> {

                    double distance = calculateDistance(
                            latitude,
                            longitude,
                            station.getLatitude(),
                            station.getLongitude()
                    );

                    return new NearbyStationResponse(
                            station.getId(),
                            station.getName(),
                            station.getAddress(),
                            station.getCity(),
                            station.getState(),
                            station.getLatitude(),
                            station.getLongitude(),
                            distance
                    );
                })
                .filter(station -> station.getDistanceKm() <= radiusKm)
                .sorted(Comparator.comparingDouble(
                        NearbyStationResponse::getDistanceKm
                ))
                .toList();
    }

    private double calculateDistance(
            double latitude1,
            double longitude1,
            double latitude2,
            double longitude2
    ) {

        final double EARTH_RADIUS_KM = 6371.0;

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);

        double a =
                Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        +
                        Math.cos(Math.toRadians(latitude1))
                                * Math.cos(Math.toRadians(latitude2))
                                * Math.sin(lonDistance / 2)
                                * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(
                Math.sqrt(a),
                Math.sqrt(1 - a)
        );

        return EARTH_RADIUS_KM * c;
    }
}