package ru.practicum.shareit.gateway;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;

@Service
public class ValidationService {

    public void validateItemDto(ItemDto itemDto) {
        if (itemDto.getName() == null || itemDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Имя вещи не может быть пустым");
        }
        if (itemDto.getDescription() == null || itemDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Описание вещи не может быть пустым");
        }
        if (itemDto.getRequestId() != null && !isValidRequestId(itemDto.getRequestId())) {
            throw new IllegalArgumentException("Некорректный ID запроса");
        }
    }

    private boolean isValidRequestId(Long requestId) {
        return true;
    }
}
