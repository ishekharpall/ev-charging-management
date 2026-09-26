package com.evplatform.booking_service.mapper;

import com.evplatform.booking_service.dto.BookingResponse;
import com.evplatform.booking_service.dto.CreateBookingRequest;
import com.evplatform.booking_service.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toEntity(CreateBookingRequest request) {

        Booking booking = new Booking();

        booking.setUserId(request.getUserId());
        booking.setVehicleId(request.getVehicleId());
        booking.setStationId(request.getStationId());
        booking.setChargerId(request.getChargerId());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());

        return booking;
    }

    public BookingResponse toResponse(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getUserId(),
                booking.getVehicleId(),
                booking.getStationId(),
                booking.getChargerId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }
}