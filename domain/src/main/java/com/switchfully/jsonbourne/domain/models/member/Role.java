package com.switchfully.jsonbourne.domain.models.member;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {

    ADMIN("admin"),
    LIBRARIAN("librarian");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getCode() {
        return name;
    }

    @JsonCreator
    public static Role getRoleFromCode(String value) {
        for (Role role : Role.values()) {
            if (role.getCode().equals(value)) {
                return role;
            }
        }
        return null;
    }
}
