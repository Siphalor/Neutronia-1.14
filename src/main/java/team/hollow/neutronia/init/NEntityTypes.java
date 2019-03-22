package team.hollow.neutronia.init;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import team.hollow.neutronia.entity.*;
import team.hollow.neutronia.entity.passive.BlackBearEntity;
import team.hollow.neutronia.entity.passive.EntityPenguin;
import team.hollow.neutronia.entity.passive.GrizzlyBearEntity;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;
import team.hollow.neutronia.utils.registry.EntityRegistryBuilder;

public class NEntityTypes {

    public static final EntityType<VillagerPlusEntity> VILLAGER_PLUS;
    public static final EntityType<BlackBearEntity> BLACK_BEAR;
    public static final EntityType<GrizzlyBearEntity> GRIZZLY_BEAR;
    public static final EntityType<SocialVillager> SOCIAL_VILLAGER;
    public static final EntityType<EntityOlDiggy> OL_DIGGY;
    public static final EntityType<ArcticWolfEntity> ARCTIC_WOLF;
    public static final EntityType<EntityJungleFrog> JUNGLE_FROG;
    public static final EntityType<AxolotlEntity> AXOLOTL;
    public static final EntityType<EntityPenguin> PENGUIN;
    public static final EntityType<EntityGreatHunger> GREAT_HUNGER;
    public static final EntityType<EntityShadowPhantom> SHADOW_PHANTOM;
    public static final EntityType<EntityEnderPhantom> ENDER_PHANTOM;
    public static final EntityType<EntityBloodPhantom> BLOOD_PHANTOM;
    public static final EntityType<AlbadonEntity> ALBADON;

    static {
        VILLAGER_PLUS = EntityRegistryBuilder
                .<VillagerPlusEntity>createBuilder("villager_plus")
                .entity((var1, var2) -> new VillagerPlusEntity(var2))
                .category(EntityCategory.CREATURE)
                .egg(5651507, 12422002)
                .size(EntitySize.constant(0.6F, 1.95F))
                .build();
        BLACK_BEAR = EntityRegistryBuilder
                .<BlackBearEntity>createBuilder("black_bear")
                .entity((var1, var2) -> new BlackBearEntity(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x0f0f0f, 0x1c1c1c)
                .size(EntitySize.constant(1.3F, 1.4F))
                .build();
        GRIZZLY_BEAR = EntityRegistryBuilder
                .<GrizzlyBearEntity>createBuilder("grizzly_bear")
                .entity((var1, var2) -> new GrizzlyBearEntity(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x281b15, 0x412e25)
                .size(EntitySize.constant(1.3F, 1.4F))
                .build();
        SOCIAL_VILLAGER = EntityRegistryBuilder
                .<SocialVillager>createBuilder("social_villager")
                .entity((var1, var2) -> new SocialVillager(var2))
                .category(EntityCategory.CREATURE)
                .egg(5651507, 12422002)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        OL_DIGGY = EntityRegistryBuilder
                .<EntityOlDiggy>createBuilder("ol_diggy")
                .entity((var1, var2) -> new EntityOlDiggy(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x172528, 0x5d7258)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        ARCTIC_WOLF = EntityRegistryBuilder
                .<ArcticWolfEntity>createBuilder("arctic_wolf")
                .entity((var1, var2) -> new ArcticWolfEntity(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x172528, 0x5d7258)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        JUNGLE_FROG = EntityRegistryBuilder
                .<EntityJungleFrog>createBuilder("jungle_frog")
                .entity((var1, var2) -> new EntityJungleFrog(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x172528, 0x5d7258)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        AXOLOTL = EntityRegistryBuilder
                .<AxolotlEntity>createBuilder("axolotl")
                .entity((var1, var2) -> new AxolotlEntity(var2))
                .category(EntityCategory.CREATURE)
                .egg(0xeab68e, 0xe08878)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        PENGUIN = EntityRegistryBuilder
                .<EntityPenguin>createBuilder("penguin")
                .entity((var1, var2) -> new EntityPenguin(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x080505, 0xcee3ff)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        GREAT_HUNGER = EntityRegistryBuilder
                .<EntityGreatHunger>createBuilder("great_hunger")
                .entity((var1, var2) -> new EntityGreatHunger(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x7c7c7c, 0x8e8e8e)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
        SHADOW_PHANTOM = EntityRegistryBuilder
                .<EntityShadowPhantom>createBuilder("shadow_phantom")
                .entity((var1, var2) -> new EntityShadowPhantom(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x172528, 0x5d7258)
                .size(EntitySize.constant(0.6F, 1.1F))
                .build();
        ENDER_PHANTOM = EntityRegistryBuilder
                .<EntityEnderPhantom>createBuilder("ender_phantom")
                .entity((var1, var2) -> new EntityEnderPhantom(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x172528, 0x5d7258)
                .size(EntitySize.constant(0.6F, 1.1F))
                .build();
        BLOOD_PHANTOM = EntityRegistryBuilder
                .<EntityBloodPhantom>createBuilder("blood_phantom")
                .entity((var1, var2) -> new EntityBloodPhantom(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x172528, 0x5d7258)
                .size(EntitySize.constant(0.6F, 1.1F))
                .build();
        ALBADON = EntityRegistryBuilder
                .<AlbadonEntity>createBuilder("albadon")
                .entity((var1, var2) -> new AlbadonEntity(var2))
                .category(EntityCategory.CREATURE)
                .egg(0x89afae, 0xba1d39)
                .size(EntitySize.constant(0.5F, 1.95F))
                .build();
    }

}