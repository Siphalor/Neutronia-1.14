package team.abnormals.neutronia.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

public class NEntityTypes {

    public static EntityType VILLAGER;

    public static void init() {
        VILLAGER = register("villager_plus", EntityType.Builder.create(VillagerPlusEntity::new, EntityCategory.CREATURE));
    }

    private static EntityType register(String string_1, EntityType.Builder<? extends Entity> entityType$Builder_1) {
        return  Registry.register(Registry.ENTITY_TYPE, new Identifier("neutronia", string_1), entityType$Builder_1.disableSaving().build(string_1));
    }

}
