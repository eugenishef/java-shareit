package ru.practicum.shareit.booking.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.service.UserRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.service.ItemRepository;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    final ItemRepository itemRepository;
    final UserRepository userRepository;
    final BookingRepository bookingRepository;

    @Override
    @Transactional
    public Booking createBooking(Long userId, Booking bookingRequest) {
        // Проверка существования пользователя
        User booker = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        // Проверка существования предмета
        Item item = itemRepository.findById(bookingRequest.getItem().getId())
                .orElseThrow(() -> new EntityNotFoundException("Предмет не найден"));

        // Проверка доступности предмета
        if (!item.getAvailable()) {
            throw new IllegalArgumentException("Предмет недоступен для бронирования");
        }

        // Установка данных бронирования
        bookingRequest.setItem(item);
        bookingRequest.setBooker(booker);
        bookingRequest.setStatus(Booking.BookingStatus.WAITING);

        // Сохранение бронирования
        return bookingRepository.save(bookingRequest);
    }

    @Override
    @Transactional
    public Booking updateBooking(Long userId, Long bookingId, Boolean approved) {
        // Проверка существования бронирования
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Бронирование не найдено"));

        // Проверка, является ли пользователь владельцем предмета
        if (!booking.getItem().getOwnerId().equals(userId)) {
            throw new IllegalArgumentException("Только владелец может подтвердить или отклонить бронирование");
        }

        // Обновление статуса бронирования
        booking.setStatus(approved ? Booking.BookingStatus.APPROVED : Booking.BookingStatus.REJECTED);

        // Сохранение обновленного бронирования
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBooking(Long userId, Long bookingId) {
        // Проверка существования бронирования
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Бронирование не найдено"));

        // Проверка, является ли пользователь автором бронирования или владельцем предмета
        if (!booking.getBooker().getId().equals(userId) && !booking.getItem().getOwnerId().equals(userId)) {
            throw new IllegalArgumentException("Нет доступа к этому бронированию");
        }

        return booking;
    }

    @Override
    public List<Booking> getAllBookings(Long userId, String state) {
        // Проверка существования пользователя
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        // Получение всех бронирований
        List<Booking> bookings;
        switch (state.toUpperCase()) {
            case "CURRENT":
                bookings = bookingRepository.findByBookerIdAndStartDateBeforeAndEndDateAfter(userId, LocalDateTime.now(), LocalDateTime.now());
                break;
            case "PAST":
                bookings = bookingRepository.findByBookerIdAndEndDateBefore(userId, LocalDateTime.now());
                break;
            case "FUTURE":
                bookings = bookingRepository.findByBookerIdAndStartDateAfter(userId, LocalDateTime.now());
                break;
            case "WAITING":
                bookings = bookingRepository.findByBookerIdAndStatus(userId, Booking.BookingStatus.WAITING);
                break;
            case "REJECTED":
                bookings = bookingRepository.findByBookerIdAndStatus(userId, Booking.BookingStatus.REJECTED);
                break;
            case "ALL":
            default:
                bookings = bookingRepository.findByBookerId(userId);
                break;
        }

        return bookings;
    }

    @Override
    public List<Booking> getAllBookingsForOwner(Long userId, String state) {
        // Проверка существования пользователя
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        // Получение всех бронирований для владельца
        List<Booking> bookings;
        switch (state.toUpperCase()) {
            case "CURRENT":
                bookings = bookingRepository.findByItemOwnerIdAndStartDateBeforeAndEndDateAfter(userId, LocalDateTime.now(), LocalDateTime.now());
                break;
            case "PAST":
                bookings = bookingRepository.findByItemOwnerIdAndEndDateBefore(userId, LocalDateTime.now());
                break;
            case "FUTURE":
                bookings = bookingRepository.findByItemOwnerIdAndStartDateAfter(userId, LocalDateTime.now());
                break;
            case "WAITING":
                bookings = bookingRepository.findByItemOwnerIdAndStatus(userId, Booking.BookingStatus.WAITING);
                break;
            case "REJECTED":
                bookings = bookingRepository.findByItemOwnerIdAndStatus(userId, Booking.BookingStatus.REJECTED);
                break;
            case "ALL":
            default:
                bookings = bookingRepository.findByItemOwnerId(userId);
                break;
        }

        return bookings;
    }
}