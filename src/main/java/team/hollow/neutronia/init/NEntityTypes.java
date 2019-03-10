package team.hollow.neutronia.init;

import net.fabricmc.fabric.api.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.entity.*;
import team.hollow.neutronia.entity.passive.BlackBearEntity;
import team.hollow.neutronia.entity.passive.BrownBearEntity;
import team.hollow.neutronia.entity.passive.EntityPenguin;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;

public class NEntityTypes {

    public static final EntityType<VillagerPlusEntity> VILLAGER_PLUS;
    public static final EntityType<BlackBearEntity> BLACK_BEAR;
    public static final EntityType<BrownBearEntity> BROWN_BEAR;
    public static final EntityType<SocialVillager> SOCIAL_VILLAGER;
    public static final EntityType<EntityOlDiggy> OL_DIGGY;
    public static final EntityType<ArcticWolfEntity> ARCTIC_WOLF;
    //    public static final EntityType JUNGLE_FROG;
    public static final EntityType<AxolotlEntity> AXOLOTL;
    public static final EntityType<EntityPenguin> PENGUIN;
    public static final EntityType<EntityGreatHunger> GREAT_HUNGER;
    public static final EntityType<EntityShadowPhantom> SHADOW_PHANTOM;
    public static final EntityType<EntityEnderPhantom> ENDER_PHANTOM;
    public static final EntityType<EntityBloodPhantom> BLOOD_PHANTOM;
    public static final EntityType<AlbadonEntity> ALBADON;

    static {
        VILLAGER_PLUS = register("villager_plus", FabricEntityTypeBuilder.<VillagerPlusEntity>create(EntityCategory.CREATURE, (var1, var2) -> new VillagerPlusEntity(var2)).size(EntitySize.constant(0.6F, 1.95F)));
        BLACK_BEAR = register("black_bear", FabricEntityTypeBuilder.<BlackBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BlackBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)));
        BROWN_BEAR = register("brown_bear", FabricEntityTypeBuilder.<BrownBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BrownBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)));
        SOCIAL_VILLAGER = register("social_villager", FabricEntityTypeBuilder.<SocialVillager>create(EntityCategory.CREATURE, (var1, var2) -> new SocialVillager(var2)).size(EntitySize.constant(0.5F, 1.95F)).trackable(64, 3));
        OL_DIGGY = register("ol_diggy", FabricEntityTypeBuilder.<EntityOlDiggy>create(EntityCategory.CREATURE, (var1, var2) -> new EntityOlDiggy(var2)).size(EntitySize.constant(0.5F, 1.95F)));
        ARCTIC_WOLF = register("arctic_wolf", FabricEntityTypeBuilder.<ArcticWolfEntity>create(EntityCategory.CREATURE, (var1, var2) -> new ArcticWolfEntity(var2)).size(EntitySize.constant(0.5F, 1.95F)));
//        JUNGLE_FROG = register("jungle_frog", FabricEntityTypeBuilder.<AxolotlEntity>create(EntityCategory.CREATURE, (var1, var2) -> new EntityJungle(var2)));
        AXOLOTL = register("axolotl", FabricEntityTypeBuilder.<AxolotlEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AxolotlEntity(var2)));
        PENGUIN = register("penguin", FabricEntityTypeBuilder.<EntityPenguin>create(EntityCategory.CREATURE, (var1, var2) -> new EntityPenguin(var2)).size(EntitySize.constant(0.6F, 1.1F)));
        GREAT_HUNGER = register("great_hunger", FabricEntityTypeBuilder.<EntityGreatHunger>create(EntityCategory.CREATURE, (var1, var2) -> new EntityGreatHunger(var2)).size(EntitySize.constant(0.6F, 1.1F)));
        SHADOW_PHANTOM = register("shadow_phantom", FabricEntityTypeBuilder.<EntityShadowPhantom>create(EntityCategory.CREATURE, (var1, var2) -> new EntityShadowPhantom(var2)).size(EntitySize.constant(0.6F, 1.1F)));
        ENDER_PHANTOM = register("ender_phantom", FabricEntityTypeBuilder.<EntityEnderPhantom>create(EntityCategory.CREATURE, (var1, var2) -> new EntityEnderPhantom(var2)).size(EntitySize.constant(0.6F, 1.1F)));
        BLOOD_PHANTOM = register("blood_phantom", FabricEntityTypeBuilder.<EntityBloodPhantom>create(EntityCategory.CREATURE, (var1, var2) -> new EntityBloodPhantom(var2)).size(EntitySize.constant(0.6F, 1.1F)));
        ALBADON = register("albadon", FabricEntityTypeBuilder.<AlbadonEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AlbadonEntity(var2)));
    }

    private static void trackEntity(EntityType<?> type, int trackingDistance, int updateIntervalTicks, boolean alwaysUpdateVelocity)
    {
        EntityTrackingRegistry.INSTANCE.register(type, trackingDistance, updateIntervalTicks, alwaysUpdateVelocity);
    }

    private static <X extends Entity> EntityType<X> register(String string_1, FabricEntityTypeBuilder<? extends Entity> entityType$Builder_1) {
        return (EntityType<X>) Registry.register(Registry.ENTITY_TYPE, new Identifier("neutronia", string_1), entityType$Builder_1.disableSaving().build());
    }

}