package team.abnormals.neutronia.init;

import net.minecraft.village.VillagerProfession;

public class ModVillagers implements VillagerProfession {

    public static VillagerProfession ARTIST;
    public static VillagerProfession RECEPTIONIST;
    public static VillagerProfession CARPENTER;
    public static VillagerProfession DOCTOR;
    public static VillagerProfession GUARD;
    public static VillagerProfession VIKING;

    public static void init() {
        ARTIST = team.abnormals.neutronia.villagers.VillagerProfession.register("artist");
        RECEPTIONIST = team.abnormals.neutronia.villagers.VillagerProfession.register("receptionist");
        CARPENTER = team.abnormals.neutronia.villagers.VillagerProfession.register("carpenter");
        DOCTOR = team.abnormals.neutronia.villagers.VillagerProfession.register("doctor");
        GUARD = team.abnormals.neutronia.villagers.VillagerProfession.register("guard");
        VIKING = team.abnormals.neutronia.villagers.VillagerProfession.register("viking");
        TradeBuilder.createRecipes();
    }

}