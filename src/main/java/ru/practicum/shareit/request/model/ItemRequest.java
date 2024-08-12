package ru.practicum.shareit.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ItemRequest {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long requesterId;
    String description;
    LocalDateTime created;
}
