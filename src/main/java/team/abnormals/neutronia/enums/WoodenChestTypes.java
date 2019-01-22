package team.abnormals.neutronia.enums;

import net.minecraft.util.Identifier;
import net.minecraft.util.StringRepresentable;

public enum WoodenChestTypes implements StringRepresentable {

    ACACIA("acacia", "acacia.png", 0),
    BIRCH("birch", "birch.png", 1),
    DARK_OAK("dark_oak", "dark_oak.png", 2),
    JUNGLE("jungle", "jungle.png", 3),
    SPRUCE("spruce", "spruce.png", 4),
    BAMBOO("bamboo", "bamboo.png", 5),
    WOODEN_DUNGEON("wooden_dungeon", "wooden_dungeon.png", 6),
    DUNGEON("dungeon", "dungeon.png", 7);

    private final String name;
    private final Identifier modelTexture;
    private final Identifier doubleModelTexture;
    private final int id;

    WoodenChestTypes(String name, String modelName, int id) {
        this.name = name + "_chest";
        this.modelTexture = new Identifier("neutronia", "textures/entity/chest/" + modelName);
        this.doubleModelTexture = new Identifier("neutronia", "textures/entity/chest/" + name + "_double.png");
        this.id = id;
    }

    public static WoodenChestTypes getFromName(String name) {
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