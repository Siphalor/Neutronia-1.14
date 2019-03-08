package team.hollow.neutronia.enums;

import net.minecraft.block.MaterialColor;
import net.minecraft.util.StringRepresentable;

public enum VanillaWoodTypes implements StringRepresentable {

    OAK(0, "oak", MaterialColor.BROWN),
    SPRUCE(1, "spruce", MaterialColor.BLACK),
    BIRCH(2, "birch", MaterialColor.SAND),
    JUNGLE(3, "jungle", MaterialColor.DIRT),
    ACACIA(4, "acacia", MaterialColor.ORANGE),
    DARK_OAK(5, "dark_oak", MaterialColor.BROWN);

    private static final VanillaWoodTypes[] META_LOOKUP = new VanillaWoodTypes[values().length];

    static {
        for (VanillaWoodTypes blockplanks$enumtype : values()) {
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

    VanillaWoodTypes(int metaIn, String nameIn, MaterialColor mapColorIn) {
        this(metaIn, nameIn, nameIn, mapColorIn);
    }

    VanillaWoodTypes(int metaIn, String nameIn, String unlocalizedNameIn, MaterialColor mapColorIn) {
        this.meta = metaIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.mapColor = mapColorIn;
    }

    public static VanillaWoodTypes byMetadata(int meta) {
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