package com.kobi.flyme.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleEnum {
    ADMIN ("ADMIN"),
    CLIENT ("CLIENT");

    RoleEnum(String role) {
    }

    @JsonCreator
    public static RoleEnum fromString(String value) {
        try {
            return RoleEnum.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null; // Set to null if the value is invalid
        }
    }



}
