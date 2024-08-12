package ru.practicum.shareit.user.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.IdGenerator;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class UserServiceImpl implements UserService {
    final List<User> users = new ArrayList<>();

    @Override
    public User createUser(User user) {
        user.setId(IdGenerator.nextId());
        users.add(user);

        return user;
    }

    @Override
    public User updateUser(Long userId, User user) {
        User existingUser = getUserById(userId);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        return existingUser;
    }

    @Override
    public User getUserById(Long userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void deleteUser(Long userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }
}
