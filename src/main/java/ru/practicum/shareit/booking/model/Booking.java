package ru.practicum.shareit.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Booking {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long itemId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long bookerId; //он же User
    LocalDateTime start;
    LocalDateTime end;
    BookingStatus status; //Item.available

    public enum BookingStatus {
        WAITING,
        APPROVED,
        REJECTED,
        RETURNED
    }
}
