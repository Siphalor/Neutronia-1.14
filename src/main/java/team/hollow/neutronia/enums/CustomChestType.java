package team.hollow.neutronia.enums;

import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import team.hollow.neutronia.Neutronia;

public enum CustomChestType implements StringIdentifiable {

    ACACIA("acacia", "acacia.png", 0),
    BIRCH("birch", "birch.png", 1),
    DARK_OAK("dark_oak", "dark_oak.png", 2),
    JUNGLE("jungle", "jungle.png", 3),
    SPRUCE("spruce", "spruce.png", 4),
    BAMBOO("bamboo", "bamboo.png", 5),
    MANGROVE("mangrove", "mangrove.png", 6),
    RED_MANGROVE("red_mangrove", "red_mangrove.png", 7),
    BAOBAB("baobab", "baobab.png", 8),
    WENGE("wenge", "wenge.png", 9),
    PURPLEHEART("purpleheart", "purpleheart.png", 10),
    LACEWOOD("lacewood", "lacewood.png", 11),
    BOLIVIAN_ROSEWOOD("bolivian_rosewood", "bolivian_rosewood.png", 12),
    GABON_EBONY("gabon_ebony", "gabon_ebony.png", 13);

    private final String name;
    private final Identifier modelTexture;
    private final Identifier doubleModelTexture;
    private final int id;

    CustomChestType(String name, String modelName, int id) {
        this.name = name + "_chest";
        this.modelTexture = new Identifier(Neutronia.MOD_ID, "textures/entity/chest/" + modelName);
        this.doubleModelTexture = new Identifier(Neutronia.MOD_ID, "textures/entity/chest/" + name + "_double.png");
        this.id = id;
    }

    public static CustomChestType getFromName(String name) {
        switch (name) {
            case "acacia_chest":
                return ACACIA;
            case "birch_chest":
                return BIRCH;
            case "dark_oak_chest":
                return DARK_OAK;
            case "jungle_chest":
                return JUNGLE;
            case "spruce_chest":
                return SPRUCE;
            case "bamboo_chest":
                return BAMBOO;
            case "mangrove_chest":
                return MANGROVE;
            case "red_mangrove_chest":
                return RED_MANGROVE;
            case "baobab_chest":
                return BAOBAB;
            case "wenge_chest":
                return WENGE;
            case "purpleheart_chest":
                return PURPLEHEART;
            case "lacewood_chest":
                return LACEWOOD;
            case "bolivian_rosewood_chest":
                return BOLIVIAN_ROSEWOOD;
            case "gabon_ebony_chest":
                return GABON_EBONY;
        }
        return ACACIA;
    }

    public int getId() {
        return id;
    }

    public Identifier getModelTexture() {
        return modelTexture;
    }

    public Identifier getDoubleModelTexture() {
        return doubleModelTexture;
    }

    @Override
    public String asString() {
        return name;
    }
}