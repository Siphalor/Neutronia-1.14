package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biomes;
import team.hollow.neutronia.village.PointOfInterestRegistry;
import team.hollow.neutronia.village.PointOfInterestTypeCustom;
import team.hollow.neutronia.village.VillagerProfessionRegistry;
import team.hollow.neutronia.village.VillagerTypeRegistry;

public class NVillagers {

    public static final VillagerProfession ARTIST;
    public static final VillagerProfession RECEPTIONIST;
    public static final VillagerProfession CARPENTER;
    public static final VillagerProfession DOCTOR;
    public static final VillagerProfession GUARD;
    public static final VillagerProfession VIKING;
    public static final VillagerProfession BARD;
    public static final VillagerProfession DRUID;
    public static final VillagerProfession ARCHER;
    public static final VillagerProfession ENCHANTER;
    public static final VillagerProfession WIZARD;
    public static final VillagerProfession ICE_CARVER;

    public static final VillagerType RED_MUSHROOM;
    public static final VillagerType BROWN_MUSHROOM;
    public static final VillagerType MOUNTAINS;
    public static final VillagerType COLD_MOUNTAINS;
    public static final VillagerType BADLANDS;
    public static final VillagerType ICE_SPIKES;
    public static final VillagerType OCEAN;

    public static final PointOfInterestType ARTIST_POI;
    public static final PointOfInterestType RECEPTIONIST_POI;
    public static final PointOfInterestType CARPENTER_POI;
    public static final PointOfInterestType DOCTOR_POI;
    public static final PointOfInterestType GUARD_POI;
    public static final PointOfInterestType VIKING_POI;
    public static final PointOfInterestType BARD_POI;
    public static final PointOfInterestType DRUID_POI;
    public static final PointOfInterestType ARCHER_POI;
    public static final PointOfInterestType ENCHANTER_POI;
    public static final PointOfInterestType WIZARD_POI;
    public static final PointOfInterestType ICE_CARVER_POI;

    static {
        ARTIST_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:artist_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.CRAFTING_TABLE), 1, null));
        RECEPTIONIST_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:receptionist_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.CRAFTING_TABLE), 1, null));
        CARPENTER_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:carpenter_poi", PointOfInterestTypeCustom.getAllStatesOf(NBlocks.SAWMILL), 1, null));
        DOCTOR_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:doctor_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.CRAFTING_TABLE), 1, null));
        GUARD_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:guard_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.DISPENSER), 1, null));
        VIKING_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:viking", PointOfInterestTypeCustom.getAllStatesOf(Blocks.CRAFTING_TABLE), 1, null));
        BARD_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:bard_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.NOTE_BLOCK), 1, null));
        DRUID_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:druid_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.CRAFTING_TABLE), 1, null));
        ARCHER_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:archer_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.CRAFTING_TABLE), 1, null));
        ENCHANTER_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:enchanter_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.BOOKSHELF), 1, null));
        WIZARD_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:wizard_poi", PointOfInterestTypeCustom.getAllStatesOf(Blocks.ENCHANTING_TABLE), 1, null));
        ICE_CARVER_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:ice_carver_poi", PointOfInterestTypeCustom.getAllStatesOf(NBlocks.ICE_SAW), 1, null));

        ARTIST = VillagerProfessionRegistry.register("artist", ARTIST_POI, ImmutableSet.of(), ImmutableSet.of());
        RECEPTIONIST = VillagerProfessionRegistry.register("receptionist", RECEPTIONIST_POI, ImmutableSet.of(), ImmutableSet.of());
        CARPENTER = VillagerProfessionRegistry.register("carpenter", CARPENTER_POI, ImmutableSet.of(), ImmutableSet.of());
        DOCTOR = VillagerProfessionRegistry.register("doctor", DOCTOR_POI, ImmutableSet.of(), ImmutableSet.of());
        GUARD = VillagerProfessionRegistry.register("guard", GUARD_POI, ImmutableSet.of(), ImmutableSet.of());
        VIKING = VillagerProfessionRegistry.register("viking", VIKING_POI, ImmutableSet.of(), ImmutableSet.of());
        BARD = VillagerProfessionRegistry.register("bard", BARD_POI, ImmutableSet.of(), ImmutableSet.of());
        DRUID = VillagerProfessionRegistry.register("druid", DRUID_POI, ImmutableSet.of(), ImmutableSet.of());
        ARCHER = VillagerProfessionRegistry.register("archer", ARCHER_POI, ImmutableSet.of(), ImmutableSet.of());
        ENCHANTER = VillagerProfessionRegistry.register("enchanter", ENCHANTER_POI, ImmutableSet.of(), ImmutableSet.of());
        WIZARD = VillagerProfessionRegistry.register("wizard", WIZARD_POI, ImmutableSet.of(), ImmutableSet.of());
        ICE_CARVER = VillagerProfessionRegistry.register("ice_carver", ICE_CARVER_POI, ImmutableSet.of(), ImmutableSet.of());
        TradeBuilder.createRecipes();

        RED_MUSHROOM = VillagerTypeRegistry.register("red_mushroom", Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE);
        BROWN_MUSHROOM = VillagerTypeRegistry.register("brown_mushroom", Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE);
        MOUNTAINS = VillagerTypeRegistry.register("mountains", Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.WOODED_MOUNTAINS);
        COLD_MOUNTAINS = VillagerTypeRegistry.register("cold_mountains", Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS);
        BADLANDS = VillagerTypeRegistry.register("badlands", Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU);
        ICE_SPIKES = VillagerTypeRegistry.register("ice_spikes", Biomes.ICE_SPIKES);
        OCEAN = VillagerTypeRegistry.register("ocean", Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
    }

}