package team.hollow.neutronia.enums;

import net.minecraft.util.StringIdentifiable;

public enum SoulStoneVariants implements StringIdentifiable {

    NORMAL_SOULSTONE(0, "soulstone"),
    CHISELED_SOULSTONE(1, "carved_soulstone"),
    SMOOTH_SOULSTONE(2, "cut_soulstone"),
    POLISHED_SOULSTONE(3, "polished_soulstone");

    private static final SoulStoneVariants[] INDEX_LOOKUP = new SoulStoneVariants[values().length];

    static {
        for (SoulStoneVariants type : values()) {
            INDEX_LOOKUP[type.getIndex()] = type;
        }
    }

    private final int index;
    private final String name;

    SoulStoneVariants(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static SoulStoneVariants byMetadata(int meta) {
        if (meta < 0 || meta >= INDEX_LOOKUP.length) {
            meta = 0;
        }

        return INDEX_LOOKUP[meta];
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
