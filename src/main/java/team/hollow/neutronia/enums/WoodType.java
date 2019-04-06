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
    BAMBOO(7, "bamboo"),
    MANGROVE(8, "mangrove"),
    RED_MANGROVE(9, "red_mangrove"),
    BAOBAB(10, "baobab"),
    WENGE(11, "wenge"),
    PURPLEHEART(12, "purpleheart"),
    LACEWOOD(13, "lacewood"),
    CHERRY(14, "cherry"),
    BOLIVIAN_ROSEWOOD(15, "bolivian_rosewood"),
    GABON_EBONY(16, "gabon_ebony");

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