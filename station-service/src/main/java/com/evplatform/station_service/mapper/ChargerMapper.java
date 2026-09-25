package com.evplatform.station_service.mapper;

import com.evplatform.station_service.dto.ChargerResponse;
import com.evplatform.station_service.dto.CreateChargerRequest;
import com.evplatform.station_service.dto.UpdateChargerRequest;
import com.evplatform.station_service.entity.Charger;
import com.evplatform.station_service.entity.Station;
import org.springframework.stereotype.Component;

@Component
public class ChargerMapper {

    public Charger toEntity(
            CreateChargerRequest request,
            Station station
    ) {

        Charger charger = new Charger();

        charger.setStation(station);
        charger.setChargerNumber(request.getChargerNumber());
        charger.setConnectorType(request.getConnectorType());
        charger.setPowerRatingKw(request.getPowerRatingKw());

        return charger;
    }

    public void updateEntity(
            Charger charger,
            UpdateChargerRequest request
    ) {

        charger.setChargerNumber(request.getChargerNumber());
        charger.setConnectorType(request.getConnectorType());
        charger.setPowerRatingKw(request.getPowerRatingKw());
        charger.setStatus(request.getStatus());
    }

    public ChargerResponse toResponse(Charger charger) {

        return new ChargerResponse(
                charger.getId(),
                charger.getStation().getId(),
                charger.getChargerNumber(),
                charger.getConnectorType(),
                charger.getPowerRatingKw(),
                charger.getStatus(),
                charger.getCreatedAt(),
                charger.getUpdatedAt()
        );
    }
}