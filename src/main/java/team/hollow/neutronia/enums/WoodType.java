package team.hollow.neutronia.enums;

import net.minecraft.util.SnakeCaseIdentifiable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum WoodType implements SnakeCaseIdentifiable {

    OAK(0, "oak"),
    SPRUCE(1, "spruce"),
    BIRCH(2, "birch"),
    JUNGLE(3, "jungle"),
    ACACIA(4, "acacia"),
    DARK_OAK(5, "dark_oak");

    public static final Set<WoodType> VANILLA_WOODS = new HashSet<>(Arrays.asList(OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK));

    private final int index;
    private final String name;

    WoodType(int metaIn, String name) {
        this.index = metaIn;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public String toSnakeCase() {
        return this.name;
    }

}