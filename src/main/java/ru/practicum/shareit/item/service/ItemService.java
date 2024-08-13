package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Long userId, Long ownerId, String name, String description, boolean available) throws IllegalAccessException;
    Item updateItem(Long userId, Long itemId, ItemDto itemDto);
    Item getItemById(Long itemId);
    List<Item> getAllItemsByOwner(Long userId);
    List<Item> searchItems(String text);
    List<Item> getAllItems();
}
