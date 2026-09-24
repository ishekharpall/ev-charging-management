package com.evplatform.station_service.mapper;

import com.evplatform.station_service.dto.CreateStationRequest;
import com.evplatform.station_service.dto.StationResponse;
import com.evplatform.station_service.dto.UpdateStationRequest;
import com.evplatform.station_service.entity.Station;
import org.springframework.stereotype.Component;

@Component
public class StationMapper {

    public Station toEntity(CreateStationRequest request) {

        return Station.builder()
                .name(request.name())
                .description(request.description())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .address(request.address())
                .city(request.city())
                .state(request.state())
                .country(request.country())
                .postalCode(request.postalCode())
                .build();
    }

    public void updateEntity(
            Station station,
            UpdateStationRequest request
    ) {

        station.setName(request.name());
        station.setDescription(request.description());
        station.setLatitude(request.latitude());
        station.setLongitude(request.longitude());
        station.setAddress(request.address());
        station.setCity(request.city());
        station.setState(request.state());
        station.setCountry(request.country());
        station.setPostalCode(request.postalCode());
        station.setStatus(request.status());
    }

    public StationResponse toResponse(Station station) {

        return new StationResponse(
                station.getId(),
                station.getName(),
                station.getDescription(),
                station.getLatitude(),
                station.getLongitude(),
                station.getAddress(),
                station.getCity(),
                station.getState(),
                station.getCountry(),
                station.getPostalCode(),
                station.getStatus(),
                station.getCreatedAt(),
                station.getUpdatedAt()
        );
    }
}