package com.martin.poestashsearcher.poeapi;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;
import tools.jackson.databind.node.ObjectNode;

/**
 * Manually defined Deserializer because the current DEDUCTION based {@link JsonTypeInfo} does not
 * take missing fields into account to deduce the subtype.
 * (and therefore considers the deserialiazition of an {@link Item} ambiguous)
 */
public class ItemDeserializer extends StdDeserializer<Item> {

    protected ItemDeserializer() {
        super(Item.class);
    }

    @Override
    public Item deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        ObjectNode node = p.readValueAsTree();
        if(node.get("stackSize") != null) {
            return new Stackable(node.get("name").asString(), node.get("baseType").asString(), node.get("stackSize").asInt(), node.get("maxStackSize").asInt());
        } else if (node.get("rarity") != null) {
            return new Equipment(node.get("name").asString(), node.get("baseType").asString(), node.get("rarity").asString());
        } else {
            return new Item(node.get("name").asString(), node.get("baseType").asString());
        }
    }

}
