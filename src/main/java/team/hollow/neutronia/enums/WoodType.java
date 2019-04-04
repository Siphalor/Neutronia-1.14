package team.hollow.neutronia.enums;

import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum WoodType implements StringRepresentable {

    OAK(0, "oak"),
    SPRUCE(1, "spruce"),
    BIRCH(2, "birch"),
    JUNGLE(3, "jungle"),
    ACACIA(4, "acacia"),
    DARK_OAK(5, "dark_oak"),
    PALM(6, "palm"),
    BAMBOO(6, "bamboo"),
    MANGROVE(7, "mangrove"),
    RED_MANGROVE(8, "red_mangrove"),
    BAOBAB(9, "baobab"),
    WENGE(9, "wenge"),
    PURPLEHEART(9, "purpleheart"),
    LACEWOOD(9, "lacewood"),
    CHERRY(10, "cherry"),
    BOLIVIAN_ROSEWOOD(11, "bolivian_rosewood"),
    GABON_EBONY(12, "gabon_ebony");

    public static final Set<WoodType> VANILLA_WOODS = new HashSet<>(Arrays.asList(OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK));
    public static final Set<WoodType> MODDED_WOODS = new HashSet<>(Arrays.asList(PALM, BAMBOO, MANGROVE, RED_MANGROVE, BAOBAB, WENGE, PURPLEHEART, LACEWOOD, CHERRY, BOLIVIAN_ROSEWOOD, GABON_EBONY));

    private final int index;
    private final String name;

    WoodType(int metaIn, String name) {
        this.index = metaIn;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public String asString() {
        return this.name;
    }

}