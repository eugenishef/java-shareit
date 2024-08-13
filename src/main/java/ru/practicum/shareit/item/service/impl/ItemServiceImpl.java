package ru.practicum.shareit.item.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.IdGenerator;
import ru.practicum.shareit.exception.InvalidItemException;
import ru.practicum.shareit.exception.ItemNotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class ItemServiceImpl implements ItemService {
    final List<Item> items = new ArrayList<>();

    @Override
    public Item createItem(Long userId, Long ownerId, String name, String description, boolean available) throws IllegalAccessException {
        if (name == null || name.isEmpty()) {
            throw new InvalidItemException("Название не может быть пустым");
        }

        Item item = new Item(IdGenerator.nextId(), ownerId, name, description, available);
        items.add(item);
        return item;
    }

    @Override
    public Item updateItem(Long userId, Long itemId, ItemDto itemDto) {
        return null;
    }

    @Override
    public Item getItemById(Long itemId) {
        return items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    @Override
    public List<Item> getAllItemsByOwner(Long userId) {
        return List.of();
    }

    @Override
    public List<Item> searchItems(String text) {
        return List.of();
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }
}
