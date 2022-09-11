package com.goit.javaonline5.note.enums;

import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public enum AccessType {
    PRIVATE("Приватний"),
    PUBLIC("Публічний");

    private final String dbValue;

    public String toValue() {
        return dbValue;
    }

    private static final List<AccessType> ALL_VALUES = new ArrayList<>();

    static {
        ALL_VALUES.addAll(Arrays.asList(values()));
    }

    public static List<AccessType> getAllValues() {
        return ALL_VALUES;
    }
}
