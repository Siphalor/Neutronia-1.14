package team.hollow.neutronia.enums;

import net.minecraft.util.Identifier;
import net.minecraft.util.SnakeCaseIdentifiable;
import team.hollow.neutronia.Neutronia;

public enum CustomChestTypes implements SnakeCaseIdentifiable {

    ACACIA("acacia", "acacia.png", 0),
    BIRCH("birch", "birch.png", 1),
    DARK_OAK("dark_oak", "dark_oak.png", 2),
    JUNGLE("jungle", "jungle.png", 3),
    SPRUCE("spruce", "spruce.png", 4);

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
    public String toSnakeCase() {
        return name;
    }
}