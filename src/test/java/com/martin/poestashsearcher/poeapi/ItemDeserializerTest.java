package com.martin.poestashsearcher.poeapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.ObjectMapper;

public class ItemDeserializerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test 
    void deserializeItem() {
        String json = "{\"name\": \"\", \"baseType\": \"Omen of Amelioration\"}";
        Item expected = new Item("", "Omen of Amelioration");
        Item deserialized = objectMapper.readValue(json, Item.class);
        assertEquals(expected, deserialized);
    }

    @Test
    void deserializeEquipment() {
        String json = "{\"name\": \"Mageblood\", \"baseType\": \"HeavyBelt\", \"rarity\": \"Unique\"}";
        Equipment expected = new Equipment("Mageblood", "HeavyBelt", "Unique");
        Item deserialized = objectMapper.readValue(json, Item.class);
        assertEquals(expected, deserialized);
    }

    @Test
    void deserializeStackable() {
        String json = "{\"name\": \"\", \"baseType\": \"Mirror of Kalandra\", \"stackSize\": 3, \"maxStackSize\": 10000}";
        Stackable expected = new Stackable("", "Mirror of Kalandra", 3, 10000);
        Item deserialized = objectMapper.readValue(json, Item.class);
        assertEquals(expected, deserialized);
    }

}
