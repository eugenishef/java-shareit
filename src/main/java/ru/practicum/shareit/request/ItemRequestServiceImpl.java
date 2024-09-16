package ru.practicum.shareit.request;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {
    final ItemRequestRepository itemRequestRepository;

    @Override
    @Transactional
    public ItemRequest createRequest(Long userId, ItemRequestDto requestDto) {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setRequesterId(userId);
        itemRequest.setDescription(requestDto.getDescription());
        itemRequest.setCreated(LocalDateTime.now());
        return itemRequestRepository.save(itemRequest);
    }

    @Override
    public List<ItemRequest> getUserRequests(Long userId) {
        return itemRequestRepository.findByRequesterId(userId);
    }

    @Override
    public List<ItemRequest> getAllRequests(Long userId) {
        return itemRequestRepository.findAllByOrderByCreatedDesc();
    }

    @Override
    public ItemRequest getRequestById(Long requestId) {
        return itemRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Запрос не найден"));
    }
}
