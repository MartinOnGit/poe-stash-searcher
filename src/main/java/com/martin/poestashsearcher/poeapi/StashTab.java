package com.martin.poestashsearcher.poeapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tools.jackson.databind.ObjectMapper;

public class StashTab {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    int numTabs;
    List<Item> items;

    @JsonCreator
    public StashTab(@JsonProperty("numTabs") int numTabs, @JsonProperty("items") List<Item> items) {
        this.numTabs = numTabs;
        this.items = items;
    }

    public int getNumTabs() {
        return numTabs;
    }

    public List<Item> getItems() {
        return items;
    }

    public static StashTab read(String json) {
        return objectMapper.readValue(json, StashTab.class);
    }

    @Override
    public String toString() {
        return "StashTab(" + numTabs + ", " + items.size() + ")";
    }
}
