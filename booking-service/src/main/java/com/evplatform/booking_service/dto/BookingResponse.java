package com.evplatform.booking_service.dto;

import com.evplatform.booking_service.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BookingResponse {

    private UUID id;
    private UUID userId;
    private UUID vehicleId;
    private UUID stationId;
    private UUID chargerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}