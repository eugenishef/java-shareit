package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(ItemController.ITEMS_BASE_PATH)
@Validated
@RestController
@RequiredArgsConstructor
public class ItemController {
    public static final String ITEMS_BASE_PATH = "/items";
    public static final String ITEM_ID_PATH = "/{item-id}";
    public static final String SEARCH_PATH = "/search";
    public static final String USER_HEADER = "X-Sharer-User-Id";

    final ItemService itemService;

    @GetMapping(ITEM_ID_PATH)
    @ResponseStatus(HttpStatus.OK)
    public Item getItem(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItemsByOwner(@RequestHeader(USER_HEADER) Long userId) {
        return itemService.getAllItemsByOwner(userId);
    }

    @GetMapping(ITEMS_BASE_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestHeader(USER_HEADER) Long userId, @Valid @RequestBody ItemDto itemDto) {
        return itemService.createItem(userId, itemDto);
    }

    @PatchMapping(ITEM_ID_PATH)
    public ResponseEntity<Item> updateItem(@RequestHeader(USER_HEADER) Long userId,
                                           @PathVariable Long itemId,
                                           @RequestBody ItemDto itemDto) {
        Item updatedItem = itemService.updateItem(userId, itemId, itemDto);
        return ResponseEntity.ok(updatedItem);
    }

    @GetMapping(SEARCH_PATH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Item>> searchItems(@RequestParam String text) {
        try {
            List<Item> searchResults = itemService.searchItems(text);
            return ResponseEntity.ok(searchResults);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}
