package team.hollow.neutronia.enums;

import net.minecraft.block.MaterialColor;
import net.minecraft.util.StringRepresentable;

public enum VanillaMinusOakWoodTypes implements StringRepresentable {
    SPRUCE(0, "spruce", MaterialColor.SPRUCE),
    BIRCH(1, "birch", MaterialColor.SAND),
    JUNGLE(2, "jungle", MaterialColor.DIRT),
    ACACIA(3, "acacia", MaterialColor.ORANGE),
    DARK_OAK(4, "dark_oak", MaterialColor.BROWN);

    private static final VanillaMinusOakWoodTypes[] META_LOOKUP = new VanillaMinusOakWoodTypes[values().length];

    static {
        for (VanillaMinusOakWoodTypes blockplanks$enumtype : values()) {
            META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
        }
    }

    private final int meta;
    private final String name;
    private final String unlocalizedName;
    /**
     * The color that represents this entry on a map.
     */
    private final MaterialColor mapColor;

    VanillaMinusOakWoodTypes(int metaIn, String nameIn, MaterialColor mapColorIn) {
        this(metaIn, nameIn, nameIn, mapColorIn);
    }

    VanillaMinusOakWoodTypes(int metaIn, String nameIn, String unlocalizedNameIn, MaterialColor mapColorIn) {
        this.meta = metaIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.mapColor = mapColorIn;
    }

    public static VanillaMinusOakWoodTypes byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMetadata() {
        return this.meta;
    }

    /**
     * The color which represents this entry on a map.
     */
    public MaterialColor getMapColor() {
        return this.mapColor;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }

    public String getTranslationKey() {
        return this.unlocalizedName;
    }
}