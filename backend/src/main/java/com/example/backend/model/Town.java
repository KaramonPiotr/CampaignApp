package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Town {
    CRACOW("Cracow"),
    WARSAW("Warsaw");

    private final String town;

    Town(String town) {
        this.town = town;
    }

    @JsonCreator
    public static Town fromValue(String value) {
        for (Town town : Town.values()) {
            if (town.town.equalsIgnoreCase(value)) {
                return town;
            }
        }
        throw new IllegalArgumentException("Invalid value for Town: " + value);
    }

    @JsonValue
    public String getValue() {
        return town;
    }

}
