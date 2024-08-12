package ru.practicum.shareit.item.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(ItemController.ITEMS_BASE_PATH)
@RestController
@RequiredArgsConstructor
public class ItemController {
    public static final String ITEMS_BASE_PATH = "/items";
    final ItemService itemService;

    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Item getItem(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAllItemsByOwner(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                           @RequestBody Item item) throws IllegalAccessException {
        if (item.getOwnerId() == null) {
            item.setOwnerId(userId);
        }
        return itemService.createItem(userId, item.getOwnerId(), item.getName(), item.getDescription(), true);
    }

    @PatchMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Item updateItem(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long itemId, @RequestBody ItemDto itemDto) {
        return itemService.updateItem(userId, itemId, itemDto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> searchItems(@RequestParam String text) {
        return itemService.searchItems(text);
    }
}
