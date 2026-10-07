package com.martin.poestashsearcher.poeapi;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ValueDeserializer.None.class)
public class Equipment extends Item {

    final String rarity;

    public Equipment(String name, String baseType, String rarity) {
        super(name, baseType);
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return "Equipment(" + name + ", " + baseType + ", " + rarity + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipment other = (Equipment) obj;
        if (rarity == null) {
            if (other.rarity != null)
                return false;
        } else if (!rarity.equals(other.rarity))
            return false;
        return true;
    }

}
