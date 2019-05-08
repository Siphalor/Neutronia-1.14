package team.hollow.neutronia.enums;

import net.minecraft.util.SnakeCaseIdentifiable;

public enum SoulStoneVariants implements SnakeCaseIdentifiable {

    NORMAL_SOULSTONE(0, "soulstone"),
    CHISELED_SOULSTONE(1, "carved_soulstone"),
    SMOOTH_SOULSTONE(2, "cut_soulstone"),
    POLISHED_SOULSTONE(3, "polished_soulstone");

    private final int index;
    private final String name;

    SoulStoneVariants(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return this.name;
    }

    public String toSnakeCase() {
        return this.name;
    }

}
