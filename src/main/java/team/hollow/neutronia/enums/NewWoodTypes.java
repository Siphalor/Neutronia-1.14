package team.hollow.neutronia.enums;

import net.minecraft.block.MaterialColor;
import net.minecraft.util.StringRepresentable;

public enum NewWoodTypes implements StringRepresentable {

    WILLOW(0, "willow", MaterialColor.LIME),
    PALM(1, "palm", MaterialColor.DIRT),
    CHERRY(2, "cherry", MaterialColor.RED),
    MANGROVE(3, "mangrove", MaterialColor.GRAY),
    RED_MANGROVE(4, "red_mangrove", MaterialColor.PINK),
    BAOBAB(5, "baobab", MaterialColor.PURPLE),
    WENGE(6, "wenge", MaterialColor.BROWN),
    PURPLEHEART(7, "purpleheart", MaterialColor.PINK);

    private static final NewWoodTypes[] META_LOOKUP = new NewWoodTypes[values().length];

    static {
        for (NewWoodTypes blockplanks$enumtype : values()) {
            META_LOOKUP[blockplanks$enumtype.getIndex()] = blockplanks$enumtype;
        }
    }

    private final int meta;
    private final String name;
    private final String unlocalizedName;
    /**
     * The color that represents this entry on a map.
     */
    private final MaterialColor mapColor;

    NewWoodTypes(int metaIn, String nameIn, MaterialColor mapColorIn) {
        this(metaIn, nameIn, nameIn, mapColorIn);
    }

    NewWoodTypes(int metaIn, String nameIn, String unlocalizedNameIn, MaterialColor mapColorIn) {
        this.meta = metaIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.mapColor = mapColorIn;
    }

    public static NewWoodTypes byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getIndex() {
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