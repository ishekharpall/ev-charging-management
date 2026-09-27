package com.evplatform.charging_service.dto;

import com.evplatform.charging_service.dto.BookingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class BookingServiceResponse {

    private UUID id;
    private UUID userId;
    private UUID vehicleId;
    private UUID stationId;
    private UUID chargerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
}