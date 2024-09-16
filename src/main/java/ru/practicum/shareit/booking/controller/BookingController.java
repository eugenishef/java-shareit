package ru.practicum.shareit.booking.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping(BookingController.BOOKING_BASE_PATH)
@RequiredArgsConstructor
public class BookingController {
    final BookingService bookingService;
    public static final String BOOKING_BASE_PATH = "/bookings";
    public static final String BOOKING_ID_PATH = "/{booking-id}";
    public static final String BOOKING_OWNER_PATH = "/owner";
    public static final String USER_HEADER = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestHeader(USER_HEADER) Long userId,
                                 @Valid @RequestBody Booking bookingRequest) {
        return bookingService.createBooking(userId, bookingRequest);
    }

    @PatchMapping(BOOKING_ID_PATH)
    public ResponseEntity<Booking> updateBooking(@RequestHeader(USER_HEADER) Long userId,
                                                 @PathVariable Long bookingId,
                                                 @RequestParam Boolean approved) {
        Booking updatedBooking = bookingService.updateBooking(userId, bookingId, approved);
        return ResponseEntity.ok(updatedBooking);
    }

    @GetMapping(BOOKING_ID_PATH)
    public ResponseEntity<Booking> getBooking(@RequestHeader(USER_HEADER) Long userId,
                                              @PathVariable Long bookingId) {
        Booking booking = bookingService.getBooking(userId, bookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings(@RequestHeader(USER_HEADER) Long userId,
                                        @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getAllBookings(userId, state);
    }

    @GetMapping(BOOKING_OWNER_PATH)
    public List<Booking> getAllBookingsForOwner(@RequestHeader(USER_HEADER) Long userId,
                                                @RequestParam(defaultValue = "ALL") String state) {
        return bookingService.getAllBookingsForOwner(userId, state);
    }
}