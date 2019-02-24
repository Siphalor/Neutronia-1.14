package team.abnormals.neutronia.init;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.entity.AxolotlEntity;
import team.abnormals.neutronia.entity.EntityOlDiggy;
import team.abnormals.neutronia.entity.SocialVillager;
import team.abnormals.neutronia.entity.passive.BlackBearEntity;
import team.abnormals.neutronia.entity.passive.BrownBearEntity;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;
import team.abnormals.neutronia.entity.ArcticWolfEntity;

public class NEntityTypes {

    public static final EntityType VILLAGER_PLUS;
    public static final EntityType BLACK_BEAR;
    public static final EntityType BROWN_BEAR;
    public static final EntityType SOCIAL_VILLAGER;
    public static final EntityType OL_DIGGY;
    public static final EntityType ARCTIC_WOLF;
//    public static final EntityType JUNGLE_FROG;
    public static final EntityType AXOLOTL;

    static {
        VILLAGER_PLUS = register("villager_plus", FabricEntityTypeBuilder.<VillagerPlusEntity>create(EntityCategory.CREATURE, (var1, var2) -> new VillagerPlusEntity(var2)).size(EntitySize.constant(0.6F, 1.95F)));
        BLACK_BEAR = register("black_bear", FabricEntityTypeBuilder.<BlackBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BlackBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)));
        BROWN_BEAR = register("brown_bear", FabricEntityTypeBuilder.<BrownBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BrownBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)));
        SOCIAL_VILLAGER = register("social_villager", FabricEntityTypeBuilder.<SocialVillager>create(EntityCategory.CREATURE, (var1, var2) -> new SocialVillager(var2)).size(EntitySize.constant(0.5F, 1.95F)).trackable(64, 3));
        OL_DIGGY = register("ol_diggy", FabricEntityTypeBuilder.<EntityOlDiggy>create(EntityCategory.CREATURE, (var1, var2) -> new EntityOlDiggy(var2)).size(EntitySize.constant(0.5F, 1.95F)));
        ARCTIC_WOLF = register("arctic_wolf", FabricEntityTypeBuilder.<ArcticWolfEntity>create(EntityCategory.CREATURE, (var1, var2) -> new ArcticWolfEntity(var2)).size(EntitySize.constant(0.5F, 1.95F)));
//        JUNGLE_FROG = register("jungle_frog", FabricEntityTypeBuilder.<AxolotlEntity>create(EntityCategory.CREATURE, (var1, var2) -> new EntityJungle(var2)));
        AXOLOTL = register("axolotl", FabricEntityTypeBuilder.<AxolotlEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AxolotlEntity(var2)));
    }

    private static EntityType register(String string_1, FabricEntityTypeBuilder<? extends Entity> entityType$Builder_1) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier("neutronia", string_1), entityType$Builder_1.disableSaving().build());
    }

}