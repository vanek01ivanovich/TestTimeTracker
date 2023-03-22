package com.example.demo.data.enums;

import java.util.Arrays;

public enum ERole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ERole getRoleByName(String name) {
        return Arrays.stream(ERole.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ERole with value " + name + " is not found"));
    }

}
