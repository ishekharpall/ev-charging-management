package com.evplatform.booking_service.service;

import com.evplatform.booking_service.client.StationServiceClient;
import com.evplatform.booking_service.client.UserServiceClient;
import com.evplatform.booking_service.dto.BookingResponse;
import com.evplatform.booking_service.dto.CreateBookingRequest;
import com.evplatform.booking_service.entity.Booking;
import com.evplatform.booking_service.entity.BookingStatus;
import com.evplatform.booking_service.exception.BookingNotFoundException;
import com.evplatform.booking_service.mapper.BookingMapper;
import com.evplatform.booking_service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserServiceClient userServiceClient;
    private final StationServiceClient stationServiceClient;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            BookingMapper bookingMapper,
            UserServiceClient userServiceClient,
            StationServiceClient stationServiceClient
    ) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.userServiceClient = userServiceClient;
        this.stationServiceClient = stationServiceClient;
    }

    @Override
    public BookingResponse createBooking(CreateBookingRequest request) {

        // 1. Validate time range
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }

        // 2. Validate user + vehicle ownership
        userServiceClient.getUserVehicle(
                request.getUserId(),
                request.getVehicleId()
        );

        // 3. Validate station + charger ownership
        stationServiceClient.getStationCharger(
                request.getStationId(),
                request.getChargerId()
        );

        // 4. Check charger booking conflict
        boolean alreadyBooked =
                bookingRepository
                        .existsByChargerIdAndStatusInAndStartTimeLessThanAndEndTimeGreaterThan(
                                request.getChargerId(),
                                List.of(
                                        BookingStatus.PENDING,
                                        BookingStatus.CONFIRMED
                                ),
                                request.getEndTime(),
                                request.getStartTime()
                        );

        if (alreadyBooked) {
            throw new IllegalStateException(
                    "Charger is already booked for the requested time"
            );
        }

        // 5. Create booking entity
        Booking booking = bookingMapper.toEntity(request);

        // 6. Save booking
        Booking savedBooking = bookingRepository.save(booking);

        // 7. Return response
        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    public BookingResponse getBooking(UUID bookingId) {

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException(bookingId)
                );

        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getUserBookings(UUID userId) {

        return bookingRepository.findByUserId(userId)
                .stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

//    @Override
//    public void cancelBooking(UUID bookingId) {
//
//        Booking booking = bookingRepository
//                .findById(bookingId)
//                .orElseThrow(() ->
//                        new RuntimeException(
//                                "Booking not found with id: " + bookingId
//                        )
//                );
//
//        booking.setStatus(BookingStatus.CANCELLED);
//
//        bookingRepository.save(booking);
//    }

    @Override
    public void cancelBooking(UUID bookingId) {

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException(bookingId)
                );

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException(
                    "Booking is already cancelled"
            );
        }

        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed booking cannot be cancelled"
            );
        }

        booking.setStatus(BookingStatus.CANCELLED);

        bookingRepository.save(booking);
    }

    @Override
    public BookingResponse confirmBooking(UUID bookingId) {

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElseThrow(() ->
                        new BookingNotFoundException(bookingId)
                );

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException(
                    "Cancelled booking cannot be confirmed"
            );
        }

        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed booking cannot be confirmed"
            );
        }

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            throw new IllegalStateException(
                    "Booking is already confirmed"
            );
        }

        booking.setStatus(BookingStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }
}