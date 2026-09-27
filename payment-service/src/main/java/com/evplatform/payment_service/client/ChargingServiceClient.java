package com.evplatform.payment_service.client;

import com.evplatform.payment_service.dto.ChargingSessionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "charging-service",
        url = "${services.charging-service.url}"
)
public interface ChargingServiceClient {

    @GetMapping(
            "/api/v1/charging-sessions/{sessionId}"
    )
    ChargingSessionResponse getChargingSession(
            @PathVariable UUID sessionId
    );
}