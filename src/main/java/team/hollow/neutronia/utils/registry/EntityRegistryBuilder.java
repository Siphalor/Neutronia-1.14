package team.hollow.neutronia.utils.registry;

import net.minecraft.entity.Entity;

public class EntityRegistryBuilder {

    public static final BlockRegistryBuilder INSTANCE = new BlockRegistryBuilder();
    private static Entity entity;
    private static String name;

    public static BlockRegistryBuilder getInstance(String nameIn, Entity entity) {
        name = nameIn;
        return INSTANCE;
    }

}
