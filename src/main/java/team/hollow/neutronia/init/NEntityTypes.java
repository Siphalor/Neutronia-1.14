package team.hollow.neutronia.init;

import net.fabricmc.fabric.api.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.entity.*;
import team.hollow.neutronia.entity.passive.BlackBearEntity;
import team.hollow.neutronia.entity.passive.BrownBearEntity;
import team.hollow.neutronia.entity.passive.EntityPenguin;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;
import team.hollow.neutronia.utils.registry.EntityRegistryBuilder;
import team.hollow.neutronia.utils.registry.RegistryUtils;

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
//    public static final EntityType<EntityShadowPhantom> SHADOW_PHANTOM;
//    public static final EntityType<EntityEnderPhantom> ENDER_PHANTOM;
//    public static final EntityType<EntityBloodPhantom> BLOOD_PHANTOM;
    public static final EntityType<AlbadonEntity> ALBADON;

    static {
        VILLAGER_PLUS = EntityRegistryBuilder
                .createBuilder("villager_plus", (entityType, world) -> new VillagerPlusEntity(world))
                .category(EntityCategory.CREATURE)
                .egg(5651507, 12422002)
                .size(EntitySize.constant(0.6F, 1.95F))
                .build();
        BLACK_BEAR = EntityRegistryBuilder
                .createBuilder("black_bear", (entityType, world) -> new BlackBearEntity(world))
                .category(EntityCategory.CREATURE)
                .egg(0x0f0f0f, 0x1c1c1c)
                .size(EntitySize.constant(1.3F, 1.4F))
                .build();
//        VILLAGER_PLUS = register("villager_plus", FabricEntityTypeBuilder.<VillagerPlusEntity>create(EntityCategory.CREATURE, (var1, var2) -> new VillagerPlusEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)), 5651507, 12422002);
//        BLACK_BEAR = register("black_bear", FabricEntityTypeBuilder.<BlackBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BlackBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)), 0x0f0f0f, 0x1c1c1c);
        BROWN_BEAR = register("brown_bear", FabricEntityTypeBuilder.<BrownBearEntity>create(EntityCategory.CREATURE, (var1, var2) -> new BrownBearEntity(var2)).size(EntitySize.constant(1.3F, 1.4F)), 0x281b15, 0x412e25);
        SOCIAL_VILLAGER = register("social_villager", FabricEntityTypeBuilder.<SocialVillager>create(EntityCategory.CREATURE, (var1, var2) -> new SocialVillager(var2)).size(EntitySize.constant(0.5F, 1.95F)).trackable(64, 3), 5651507, 12422002);
        OL_DIGGY = register("ol_diggy", FabricEntityTypeBuilder.<EntityOlDiggy>create(EntityCategory.CREATURE, (var1, var2) -> new EntityOlDiggy(var2)).size(EntitySize.constant(0.5F, 1.95F)), 0x172528, 0x5d7258);
        ARCTIC_WOLF = register("arctic_wolf", FabricEntityTypeBuilder.<ArcticWolfEntity>create(EntityCategory.CREATURE, (var1, var2) -> new ArcticWolfEntity(var2)).size(EntitySize.constant(0.5F, 1.95F)), 0xc8c7c3, 0x161616);
//        JUNGLE_FROG = register("jungle_frog", FabricEntityTypeBuilder.<AxolotlEntity>create(EntityCategory.CREATURE, (var1, var2) -> new EntityJungle(var2)));
        AXOLOTL = register("axolotl", FabricEntityTypeBuilder.<AxolotlEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AxolotlEntity(var2)), 0xeab68e, 0xe08878);
        PENGUIN = register("penguin", FabricEntityTypeBuilder.<EntityPenguin>create(EntityCategory.CREATURE, (var1, var2) -> new EntityPenguin(var2)).size(EntitySize.constant(0.6F, 1.1F)), 0x080505, 0xcee3ff);
        GREAT_HUNGER = register("great_hunger", FabricEntityTypeBuilder.<EntityGreatHunger>create(EntityCategory.CREATURE, (var1, var2) -> new EntityGreatHunger(var2)).size(EntitySize.constant(0.6F, 1.1F)), 0x7c7c7c, 0x8e8e8e);
//        SHADOW_PHANTOM = register("shadow_phantom", FabricEntityTypeBuilder.<EntityShadowPhantom>create(EntityCategory.CREATURE, (var1, var2) -> new EntityShadowPhantom(var2)).size(EntitySize.constant(0.6F, 1.1F)));
//        ENDER_PHANTOM = register("ender_phantom", FabricEntityTypeBuilder.<EntityEnderPhantom>create(EntityCategory.CREATURE, (var1, var2) -> new EntityEnderPhantom(var2)).size(EntitySize.constant(0.6F, 1.1F)));
//        BLOOD_PHANTOM = register("blood_phantom", FabricEntityTypeBuilder.<EntityBloodPhantom>create(EntityCategory.CREATURE, (var1, var2) -> new EntityBloodPhantom(var2)).size(EntitySize.constant(0.6F, 1.1F)));
        ALBADON = register("albadon", FabricEntityTypeBuilder.<AlbadonEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AlbadonEntity(var2)), 0x89afae, 0xba1d39);
    }

    private static void trackEntity(EntityType<?> type, int trackingDistance, int updateIntervalTicks, boolean alwaysUpdateVelocity) {
        EntityTrackingRegistry.INSTANCE.register(type, trackingDistance, updateIntervalTicks, alwaysUpdateVelocity);
    }

    private static <X extends Entity> EntityType register(String string_1, FabricEntityTypeBuilder<? extends Entity> entityType$Builder_1, int primaryEggColor, int secondaryEggColor) {
        EntityType<X> entityType = (EntityType<X>) Registry.register(Registry.ENTITY_TYPE, new Identifier(Neutronia.MOD_ID, string_1), entityType$Builder_1.disableSaving().build());
        RegistryUtils.registerItem(new SpawnEggItem(entityType, primaryEggColor, secondaryEggColor, new Item.Settings().itemGroup(ItemGroup.MISC)), String.format("%s_spawn_egg", string_1));
        return entityType;
    }

}