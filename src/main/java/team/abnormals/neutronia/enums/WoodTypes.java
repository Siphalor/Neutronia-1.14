package team.abnormals.neutronia.enums;

import net.minecraft.block.MaterialColor;
import net.minecraft.util.StringRepresentable;

public enum WoodTypes implements StringRepresentable {

    OAK(0, "oak", MaterialColor.BROWN),
    SPRUCE(1, "spruce", MaterialColor.BLACK),
    BIRCH(2, "birch", MaterialColor.SAND),
    JUNGLE(3, "jungle", MaterialColor.DIRT),
    ACACIA(4, "acacia", MaterialColor.ORANGE),
    DARK_OAK(5, "dark_oak", MaterialColor.BROWN),
    BAMBOO(6, "bamboo", MaterialColor.DIRT),
    PALM(7, "palm", MaterialColor.ORANGE),
    WILLOW(8, "willow", MaterialColor.GRASS),
    CHERRY(9, "cherry", MaterialColor.PINK);

    private static final WoodTypes[] META_LOOKUP = new WoodTypes[values().length];

    static {
        for (WoodTypes blockplanks$enumtype : values()) {
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

    WoodTypes(int metaIn, String nameIn, MaterialColor mapColorIn) {
        this(metaIn, nameIn, nameIn, mapColorIn);
    }

    WoodTypes(int metaIn, String nameIn, String unlocalizedNameIn, MaterialColor mapColorIn) {
        this.meta = metaIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.mapColor = mapColorIn;
    }

    public static WoodTypes byMetadata(int meta) {
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
    public MaterialColor getMaterialColor() {
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