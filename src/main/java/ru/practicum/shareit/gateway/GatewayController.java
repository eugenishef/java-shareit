package ru.practicum.shareit.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
    final ValidationService validationService;
    final RestTemplate restTemplate;

    @Autowired
    public GatewayController(ValidationService validationService, RestTemplate restTemplate) {
        this.validationService = validationService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/items")
    public ResponseEntity<?> validateAndCreateItem(@RequestBody ItemDto itemDto) {
        validationService.validateItemDto(itemDto);

        ResponseEntity<Item> response = restTemplate.postForEntity("http://localhost/items", itemDto, Item.class);

        return response;
    }
}