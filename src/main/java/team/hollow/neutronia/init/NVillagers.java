package team.hollow.neutronia.init;

import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biomes;

public class NVillagers implements VillagerType {

    /*public static final VillagerProfession ARTIST;
    public static final VillagerProfession RECEPTIONIST;
    public static final VillagerProfession CARPENTER;
    public static final VillagerProfession DOCTOR;
    public static final VillagerProfession GUARD;
    public static final VillagerProfession VIKING;
    public static final VillagerProfession BARD;
    public static final VillagerProfession DRUID;
    public static final VillagerProfession ARCHER;
    public static final VillagerProfession ENCHANTER;
    public static final VillagerProfession WIZARD;*/

    public static final VillagerType MUSHROOM;
    public static final VillagerType MOUNTAINS;
    public static final VillagerType COLD_MOUNTAINS;
    public static final VillagerType BADLANDS;
    public static final VillagerType ICE_SPIKES;
    public static final VillagerType OCEAN;

    static {
        /*ARTIST = team.hollow.neutronia.village.VillagerProfession.register("artist");
        RECEPTIONIST = team.hollow.neutronia.village.VillagerProfession.register("receptionist");
        CARPENTER = team.hollow.neutronia.village.VillagerProfession.register("carpenter");
        DOCTOR = team.hollow.neutronia.village.VillagerProfession.register("doctor");
        GUARD = team.hollow.neutronia.village.VillagerProfession.register("guard");
        VIKING = team.hollow.neutronia.village.VillagerProfession.register("viking");
        BARD = team.hollow.neutronia.village.VillagerProfession.register("bard");
        DRUID = team.hollow.neutronia.village.VillagerProfession.register("druid");
        ARCHER = team.hollow.neutronia.village.VillagerProfession.register("archer");
        ENCHANTER = team.hollow.neutronia.village.VillagerProfession.register("enchanter");
        WIZARD = team.hollow.neutronia.village.VillagerProfession.register("wizard");*/
        TradeBuilder.createRecipes();

        MUSHROOM = team.hollow.neutronia.village.VillagerType.register("mushroom", Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE);
        MOUNTAINS = team.hollow.neutronia.village.VillagerType.register("mountains", Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.WOODED_MOUNTAINS);
        COLD_MOUNTAINS = team.hollow.neutronia.village.VillagerType.register("cold_mountains", Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS);
        BADLANDS = team.hollow.neutronia.village.VillagerType.register("badlands", Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU);
        ICE_SPIKES = team.hollow.neutronia.village.VillagerType.register("ice_spikes", Biomes.ICE_SPIKES);
        OCEAN = team.hollow.neutronia.village.VillagerType.register("ocean", Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
    }

}