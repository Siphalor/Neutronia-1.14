package team.abnormals.neutronia.init;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.entity.SocialVillager;
import team.abnormals.neutronia.entity.passive.BlackBearEntity;
import team.abnormals.neutronia.entity.passive.BrownBearEntity;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

public class NEntityTypes {

    public static final EntityType VILLAGER_PLUS;
    public static final EntityType BLACK_BEAR;
    public static final EntityType BROWN_BEAR;
    public static final EntityType SOCIAL_VILLAGER;

    static {
        VILLAGER_PLUS = register("villager_plus", FabricEntityTypeBuilder.<VillagerPlusEntity>create(EntityCategory.CREATURE, (var1, var2) -> new VillagerPlusEntity(var2)).size(EntitySize.constant(0.6F, 1.95F)));
        BLACK_BEAR = register("black_bear", FabricEntityTypeBuilder.<BlackBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BlackBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)));
        BROWN_BEAR = register("brown_bear", FabricEntityTypeBuilder.<BrownBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BrownBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)));
        SOCIAL_VILLAGER = register("social_villager", FabricEntityTypeBuilder.<SocialVillager>create(EntityCategory.CREATURE, (var1, var2) -> new SocialVillager(var2)).size(EntitySize.constant(0.5F, 1.95F)).trackable(64, 3));
    }

    private static EntityType register(String string_1, FabricEntityTypeBuilder<? extends Entity> entityType$Builder_1) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier("neutronia", string_1), entityType$Builder_1.disableSaving().build());
    }

}