package team.hollow.neutronia.enums;

import net.minecraft.util.StringRepresentable;

public enum LadderVariants implements StringRepresentable {
    SPRUCE(0, "spruce"),
    BIRCH(1, "birch"),
    JUNGLE(2, "jungle"),
    ACACIA(3, "acacia"),
    DARK_OAK(4, "dark_oak"),
    BAMBOO(5, "bamboo");

    private final int index;
    private final String name;

    LadderVariants(int index, String nameIn) {
        this.index = index;
        this.name = nameIn;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }

}