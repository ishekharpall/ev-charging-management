package com.evplatform.booking_service.client;

import com.evplatform.booking_service.dto.ChargerServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "station-service",
        url = "${services.station-service.url}"
)
public interface StationServiceClient {

    @GetMapping("/api/v1/stations/{stationId}/chargers/{chargerId}")
    ChargerServiceResponse getStationCharger(
            @PathVariable UUID stationId,
            @PathVariable UUID chargerId
    );
}