package com.martin.poestashsearcher.poeapi;

import tools.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ItemDeserializer.class)
public class Item {

    final String name;
    final String baseType;

    public Item(String name, String baseType) {
        this.name = name;
        this.baseType = baseType;
    }

    public String getName() {
        return name;
    }

    public String getBaseType() {
        return baseType;
    }

    @Override
    public String toString() {
        return "Item(" + name + ", " + baseType + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((baseType == null) ? 0 : baseType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (baseType == null) {
            if (other.baseType != null)
                return false;
        } else if (!baseType.equals(other.baseType))
            return false;
        return true;
    }
}
