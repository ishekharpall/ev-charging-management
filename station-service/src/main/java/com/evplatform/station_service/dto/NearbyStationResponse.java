package com.evplatform.station_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class NearbyStationResponse {

    private UUID id;

    private String name;

    private String address;

    private String city;

    private String state;

    private Double latitude;

    private Double longitude;

    private double distanceKm;
}