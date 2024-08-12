package ru.practicum.shareit.common;

public class IdGenerator {
    private static long currentId = 1L;

    public static synchronized long nextId() {
        return currentId++;
    }
}
