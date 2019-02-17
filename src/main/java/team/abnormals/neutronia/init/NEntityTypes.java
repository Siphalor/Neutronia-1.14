package team.abnormals.neutronia.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.entity.passive.BlackBearEntity;
import team.abnormals.neutronia.entity.passive.BrownBearEntity;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

public class NEntityTypes {

    public static EntityType VILLAGER_PLUS;
    public static EntityType BLACK_BEAR;
    public static EntityType BROWN_BEAR;

    public static void init() {
        VILLAGER_PLUS = register("villager_plus", EntityType.Builder.create(VillagerPlusEntity::new, EntityCategory.CREATURE).setSize(0.6F, 1.95F));
        BLACK_BEAR = register("black_bear", EntityType.Builder.create(BlackBearEntity::new, EntityCategory.CREATURE).setSize(1.3F, 1.4F));
        BROWN_BEAR = register("brown_bear", EntityType.Builder.create(BrownBearEntity::new, EntityCategory.CREATURE).setSize(1.3F, 1.4F));
    }

    private static EntityType register(String string_1, EntityType.Builder<? extends Entity> entityType$Builder_1) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier("neutronia", string_1), entityType$Builder_1.disableSaving().build(string_1));
    }

}
