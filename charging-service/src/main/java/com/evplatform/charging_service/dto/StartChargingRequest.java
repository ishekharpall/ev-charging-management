package com.evplatform.charging_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StartChargingRequest {

    @NotNull
    private UUID bookingId;
}