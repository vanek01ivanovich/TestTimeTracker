package com.example.demo.data.enums;

import java.util.stream.Stream;

public enum Status {
    NOT_STARTED("NOT_STARTED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String name;

    Status(String status) {
        this.name = status;
    }

    public String getName() {
        return name;
    }

    public static Status getByStatus(String status){
        return Stream.of(Status.values())
                .filter(s -> s.name.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Status with value " + status + " is not found"));
    }
}
