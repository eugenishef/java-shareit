package ru.practicum.shareit.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BookingDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long itemId;
    LocalDateTime start;
    LocalDateTime end;
}
