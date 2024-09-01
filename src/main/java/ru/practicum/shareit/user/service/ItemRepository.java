package ru.practicum.shareit.user.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByOwnerId(Long ownerId);
    List<Item> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String text1, String text2);
}
