package team.hollow.neutronia.init;

import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biomes;

import static net.minecraft.world.biome.Biomes.*;

public class ModVillagers implements VillagerProfession, VillagerType {

    public static VillagerProfession ARTIST;
    public static VillagerProfession RECEPTIONIST;
    public static VillagerProfession CARPENTER;
    public static VillagerProfession DOCTOR;
    public static VillagerProfession GUARD;
    public static VillagerProfession VIKING;
    public static VillagerProfession BARD;
    public static VillagerProfession DRUID;
    public static VillagerProfession ARCHER;
    public static VillagerProfession ENCHANTER;
    public static VillagerProfession WIZARD;

    public static VillagerType MUSHROOM;
    public static VillagerType MOUNTAINS;
    public static VillagerType COLD_MOUNTAINS;
    public static VillagerType BADLANDS;

    public static void init() {
        ARTIST = team.hollow.neutronia.villagers.VillagerProfession.register("artist");
        RECEPTIONIST = team.hollow.neutronia.villagers.VillagerProfession.register("receptionist");
        CARPENTER = team.hollow.neutronia.villagers.VillagerProfession.register("carpenter");
        DOCTOR = team.hollow.neutronia.villagers.VillagerProfession.register("doctor");
        GUARD = team.hollow.neutronia.villagers.VillagerProfession.register("guard");
        VIKING = team.hollow.neutronia.villagers.VillagerProfession.register("viking");
        BARD = team.hollow.neutronia.villagers.VillagerProfession.register("bard");
        DRUID = team.hollow.neutronia.villagers.VillagerProfession.register("druid");
        ARCHER = team.hollow.neutronia.villagers.VillagerProfession.register("archer");
        ENCHANTER = team.hollow.neutronia.villagers.VillagerProfession.register("enchanter");
        WIZARD = team.hollow.neutronia.villagers.VillagerProfession.register("wizard");
        TradeBuilder.createRecipes();

        MUSHROOM = team.hollow.neutronia.villagers.VillagerType.register("mushroom", MUSHROOM_FIELDS, MUSHROOM_FIELD_SHORE);
        MOUNTAINS = team.hollow.neutronia.villagers.VillagerType.register("mountains", Biomes.MOUNTAINS, MOUNTAIN_EDGE, WOODED_MOUNTAINS);
        COLD_MOUNTAINS = team.hollow.neutronia.villagers.VillagerType.register("cold_mountains", Biomes.MOUNTAINS, MOUNTAIN_EDGE, WOODED_MOUNTAINS);
        BADLANDS = team.hollow.neutronia.villagers.VillagerType.register("badlands", Biomes.BADLANDS, BADLANDS_PLATEAU, ERODED_BADLANDS, WOODED_BADLANDS_PLATEAU);
    }

}