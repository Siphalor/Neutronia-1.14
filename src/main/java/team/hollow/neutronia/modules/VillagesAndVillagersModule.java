package team.hollow.neutronia.modules;

import team.hollow.module_api.api.Module;
import team.hollow.neutronia.modules.villagesandvillagers.VillagerProfessionsSubModule;
import team.hollow.neutronia.modules.villagesandvillagers.VillagerTypesFeature;

public class VillagesAndVillagersModule extends Module {
	public static VillagerTypesFeature villagerTypes;
	public static VillagerProfessionsSubModule villagerProfessions;

	public VillagesAndVillagersModule() {
		super("villages-and-villagers", "Improves villages by adding new professions, biome variants and more!");

		villagerTypes = register(new VillagerTypesFeature());
		villagerProfessions = register(new VillagerProfessionsSubModule());
	}
}
