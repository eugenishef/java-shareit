package ru.practicum.shareit.booking.service;

import jakarta.transaction.Transactional;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;

public interface BookingService {
    @Transactional
    Booking createBooking(Long userId, Booking bookingRequest);

    @Transactional
    Booking updateBooking(Long userId, Long bookingId, Boolean approved);

    Booking getBooking(Long userId, Long bookingId);
    List<Booking> getAllBookings(Long userId, String state);
    List<Booking> getAllBookingsForOwner(Long userId, String state);
}