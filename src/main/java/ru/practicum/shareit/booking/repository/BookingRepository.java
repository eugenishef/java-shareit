package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookerId(Long bookerId);

    List<Booking> findByBookerIdAndStartDateBeforeAndEndDateAfter(Long bookerId, LocalDateTime startDate, LocalDateTime endDate);

    List<Booking> findByBookerIdAndEndDateBefore(Long bookerId, LocalDateTime endDate);

    List<Booking> findByBookerIdAndStartDateAfter(Long bookerId, LocalDateTime startDate);

    List<Booking> findByBookerIdAndStatus(Long bookerId, Booking.BookingStatus status);

    List<Booking> findByItemOwnerId(Long ownerId);

    List<Booking> findByItemOwnerIdAndStartDateBeforeAndEndDateAfter(Long ownerId, LocalDateTime startDate, LocalDateTime endDate);

    List<Booking> findByItemOwnerIdAndEndDateBefore(Long ownerId, LocalDateTime endDate);

    List<Booking> findByItemOwnerIdAndStartDateAfter(Long ownerId, LocalDateTime startDate);

    List<Booking> findByItemOwnerIdAndStatus(Long ownerId, Booking.BookingStatus status);
}