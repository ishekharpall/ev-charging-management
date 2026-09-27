package com.evplatform.charging_service.service;

import com.evplatform.charging_service.client.BookingServiceClient;
import com.evplatform.charging_service.dto.BookingServiceResponse;
import com.evplatform.charging_service.dto.BookingStatus;
import com.evplatform.charging_service.dto.ChargingSessionResponse;
import com.evplatform.charging_service.dto.StartChargingRequest;
import com.evplatform.charging_service.dto.StopChargingRequest;
import com.evplatform.charging_service.entity.ChargingSession;
import com.evplatform.charging_service.entity.ChargingSessionStatus;
import com.evplatform.charging_service.exception.ChargingSessionNotFoundException;
import com.evplatform.charging_service.mapper.ChargingSessionMapper;
import com.evplatform.charging_service.repository.ChargingSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ChargingSessionServiceImpl
        implements ChargingSessionService {

    private final ChargingSessionRepository chargingSessionRepository;
    private final BookingServiceClient bookingServiceClient;
    private final ChargingSessionMapper chargingSessionMapper;

    public ChargingSessionServiceImpl(
            ChargingSessionRepository chargingSessionRepository,
            BookingServiceClient bookingServiceClient,
            ChargingSessionMapper chargingSessionMapper
    ) {
        this.chargingSessionRepository = chargingSessionRepository;
        this.bookingServiceClient = bookingServiceClient;
        this.chargingSessionMapper = chargingSessionMapper;
    }

    @Override
    public ChargingSessionResponse startCharging(
            StartChargingRequest request
    ) {

        BookingServiceResponse booking =
                bookingServiceClient.getBooking(
                        request.getBookingId()
                );

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new IllegalStateException(
                    "Only a confirmed booking can start charging"
            );
        }

        if (chargingSessionRepository
                .findByBookingId(request.getBookingId())
                .isPresent()) {

            throw new IllegalStateException(
                    "Charging session already exists for this booking"
            );
        }

        ChargingSession session = new ChargingSession();

        session.setBookingId(booking.getId());
        session.setUserId(booking.getUserId());
        session.setVehicleId(booking.getVehicleId());
        session.setStationId(booking.getStationId());
        session.setChargerId(booking.getChargerId());
        session.setStartedAt(LocalDateTime.now());
        session.setStatus(ChargingSessionStatus.STARTED);

        ChargingSession savedSession =
                chargingSessionRepository.save(session);

        return chargingSessionMapper.toResponse(
                savedSession
        );
    }

    @Override
    public ChargingSessionResponse getChargingSession(
            UUID sessionId
    ) {

        ChargingSession session =
                chargingSessionRepository
                        .findById(sessionId)
                        .orElseThrow(() ->
                                new ChargingSessionNotFoundException(
                                        sessionId
                                )
                        );

        return chargingSessionMapper.toResponse(session);
    }

    @Override
    public ChargingSessionResponse stopCharging(
            UUID sessionId,
            StopChargingRequest request
    ) {

        ChargingSession session =
                chargingSessionRepository
                        .findById(sessionId)
                        .orElseThrow(() ->
                                new ChargingSessionNotFoundException(
                                        sessionId
                                )
                        );

        if (session.getStatus()
                != ChargingSessionStatus.STARTED) {

            throw new IllegalStateException(
                    "Only an active charging session can be stopped"
            );
        }

        session.setEndedAt(LocalDateTime.now());

        session.setEnergyConsumedKwh(
                request.getEnergyConsumedKwh()
        );

        session.setStatus(
                ChargingSessionStatus.COMPLETED
        );

        ChargingSession savedSession =
                chargingSessionRepository.save(session);

        bookingServiceClient.completeBooking(
                session.getBookingId()
        );

        return chargingSessionMapper.toResponse(
                savedSession
        );
    }

    @Override
    public List<ChargingSessionResponse> getUserSessions(
            UUID userId
    ) {

        return chargingSessionRepository
                .findByUserId(userId)
                .stream()
                .map(chargingSessionMapper::toResponse)
                .toList();
    }

    @Override
    public List<ChargingSessionResponse> getChargerSessions(
            UUID chargerId
    ) {

        return chargingSessionRepository
                .findByChargerId(chargerId)
                .stream()
                .map(chargingSessionMapper::toResponse)
                .toList();
    }

    @Override
    public List<ChargingSessionResponse> getSessionsByStatus(
            ChargingSessionStatus status
    ) {

        return chargingSessionRepository
                .findByStatus(status)
                .stream()
                .map(chargingSessionMapper::toResponse)
                .toList();
    }
}