package com.martin.poestashsearcher.poeapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tools.jackson.databind.ObjectMapper;

public class Item {

    String name;
    String baseType;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @JsonCreator
    public Item(@JsonProperty("name") String name, @JsonProperty("baseType") String baseType) {
        this.name = name;
        this.baseType = baseType;
    }

    public String getName() {
        return name;
    }

    public String getBaseType() {
        return baseType;
    }

    public static Item read(String json) {
        return objectMapper.readValue(json, Item.class);
    }

    @Override
    public String toString() {
        return "Item(" + name + ", " + baseType + ")";
    }
}
