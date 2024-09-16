package ru.practicum.shareit.exception;

public class BookingAccessDeniedException extends RuntimeException {
    public BookingAccessDeniedException() {
        super("Нет доступа к этому бронированию");
    }
}