package com.evplatform.charging_service.client;

import com.evplatform.charging_service.dto.BookingServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(
        name = "booking-service",
        url = "${services.booking-service.url}"
)
public interface BookingServiceClient {

    @GetMapping("/api/v1/bookings/{bookingId}")
    BookingServiceResponse getBooking(
            @PathVariable UUID bookingId
    );

    @PutMapping("/api/v1/bookings/{bookingId}/complete")
    BookingServiceResponse completeBooking(
            @PathVariable UUID bookingId
    );
}