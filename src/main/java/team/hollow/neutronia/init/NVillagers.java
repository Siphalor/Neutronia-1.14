package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableSet;
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
    /*public static final VillagerProfession RECEPTIONIST;
    public static final VillagerProfession CARPENTER;
    public static final VillagerProfession DOCTOR;
    public static final VillagerProfession GUARD;
    public static final VillagerProfession VIKING;
    public static final VillagerProfession BARD;
    public static final VillagerProfession DRUID;
    public static final VillagerProfession ARCHER;
    public static final VillagerProfession ENCHANTER;
    public static final VillagerProfession WIZARD;*/

    public static final VillagerType RED_MUSHROOM;
    public static final VillagerType BROWN_MUSHROOM;
    public static final VillagerType MOUNTAINS;
    public static final VillagerType COLD_MOUNTAINS;
    public static final VillagerType BADLANDS;
    public static final VillagerType ICE_SPIKES;
    public static final VillagerType OCEAN;

    public static final PointOfInterestType ARTIST_POI;

    static {

        ARTIST_POI = PointOfInterestRegistry.register(new PointOfInterestTypeCustom("neutronia:artist_poi", PointOfInterestTypeCustom.getAllStatesOf(NBlocks.ANDESITE_BRICKS), 1, null));

        ARTIST = VillagerProfessionRegistry.register("artist", ARTIST_POI, ImmutableSet.of(), ImmutableSet.of());
        /*RECEPTIONIST = VillagerProfessionRegistry.register("receptionist");
        CARPENTER = VillagerProfessionRegistry.register("carpenter");
        DOCTOR = VillagerProfessionRegistry.register("doctor");
        GUARD = VillagerProfessionRegistry.register("guard");
        VIKING = VillagerProfessionRegistry.register("viking");
        BARD = VillagerProfessionRegistry.register("bard");
        DRUID = VillagerProfessionRegistry.register("druid");
        ARCHER = VillagerProfessionRegistry.register("archer");
        ENCHANTER = VillagerProfessionRegistry.register("enchanter");
        WIZARD = VillagerProfessionRegistry.register("wizard");*/
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