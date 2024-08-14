package ru.practicum.shareit.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class User {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    String name;
    @NotBlank
    @Email
    String email;
}