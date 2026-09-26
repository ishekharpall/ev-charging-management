package com.evplatform.booking_service.service;

import com.evplatform.booking_service.dto.BookingResponse;
import com.evplatform.booking_service.dto.CreateBookingRequest;


import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingResponse createBooking(CreateBookingRequest request);

    BookingResponse getBooking(UUID bookingId);

    List<BookingResponse> getUserBookings(UUID userId);

    void cancelBooking(UUID bookingId);

    BookingResponse confirmBooking(UUID bookingId);
}