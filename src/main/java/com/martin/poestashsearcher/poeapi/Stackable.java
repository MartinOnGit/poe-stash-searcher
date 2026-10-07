package com.martin.poestashsearcher.poeapi;

import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ValueDeserializer.None.class)
public class Stackable extends Item {

    final int stackSize;
    final int maxStackSize;

    public Stackable(String name, String baseType, int stackSize, int maxStackSize) {
        super(name, baseType);
        this.stackSize = stackSize;
        this.maxStackSize = maxStackSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    @Override
    public String toString() {
        return "Stackable (" + ", " + name + ", " + baseType + stackSize + "/" + maxStackSize + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + stackSize;
        result = prime * result + maxStackSize;
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
        Stackable other = (Stackable) obj;
        if (stackSize != other.stackSize)
            return false;
        if (maxStackSize != other.maxStackSize)
            return false;
        return true;
    }

}
