package team.hollow.neutronia.enums;

import net.minecraft.util.Identifier;
import net.minecraft.util.StringRepresentable;
import team.hollow.neutronia.Neutronia;

public enum CustomChestTypes implements StringRepresentable {

    ACACIA("acacia", "acacia.png", 0),
    BIRCH("birch", "birch.png", 1),
    DARK_OAK("dark_oak", "dark_oak.png", 2),
    JUNGLE("jungle", "jungle.png", 3),
    SPRUCE("spruce", "spruce.png", 4),
    BAMBOO("bamboo", "bamboo.png", 5),
    WOODEN_DUNGEON("wooden_dungeon", "wooden_dungeon.png", 6),
    DUNGEON("dungeon", "dungeon.png", 7),
    STONE("stone", "stone.png", 8),
    STONE_BRICK("stone_brick", "stone_brick.png", 9),
    COBBLESTONE("cobblestone", "cobblestone.png", 10),
    MOSSY_COBBLESTONE("mossy_cobblestone", "mossy_cobblestone.png", 11),
    MANGROVE("mangrove", "mangrove.png", 12);

    private final String name;
    private final Identifier modelTexture;
    private final Identifier doubleModelTexture;
    private final int id;

    CustomChestTypes(String name, String modelName, int id) {
        this.name = name + "_chest";
        this.modelTexture = new Identifier(Neutronia.MOD_ID, "textures/entity/chest/" + modelName);
        this.doubleModelTexture = new Identifier(Neutronia.MOD_ID, "textures/entity/chest/" + name + "_double.png");
        this.id = id;
    }

    public static CustomChestTypes getFromName(String name) {
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
            case "wooden_dungeon_chest":
                return WOODEN_DUNGEON;
            case "dungeon_chest":
                return DUNGEON;
            case "stone_chest":
                return STONE;
            case "stone_brick_chest":
                return STONE_BRICK;
            case "cobblestone_chest":
                return COBBLESTONE;
            case "mossy_cobblestone_chest":
                return MOSSY_COBBLESTONE;
            case "mangrove_chest":
                return MANGROVE;
        }
        return ACACIA;
    }

    public static CustomChestTypes getFromID(int id) {
        switch (id) {
            case 0:
                return ACACIA;
            case 1:
                return BIRCH;
            case 2:
                return DARK_OAK;
            case 3:
                return JUNGLE;
            case 4:
                return SPRUCE;
            case 5:
                return BAMBOO;
            case 6:
                return WOODEN_DUNGEON;
            case 7:
                return DUNGEON;
            case 8:
                return STONE;
            case 9:
                return STONE_BRICK;
            case 10:
                return COBBLESTONE;
            case 11:
                return MOSSY_COBBLESTONE;
            case 12:
                return MANGROVE;
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