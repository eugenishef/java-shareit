package ru.practicum.shareit.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long bookingId) {
        super("Бронирование с ID " + bookingId + " не найдено");
    }
}