package com.goit.javaonline5.note.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccessType {
    PRIVATE("private"),
    PUBLIC("public");

    private final String dbValue;

    public String toValue() {
        return dbValue;
    }
}
