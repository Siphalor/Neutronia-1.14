package team.hollow.neutronia.modules;

import team.hollow.abnormalib.modules.api.MainModule;
import team.hollow.neutronia.modules.villagesandvillagers.VillagerProfessionsSubModule;
import team.hollow.neutronia.modules.villagesandvillagers.VillagerTypesFeature;

public class VillagesAndVillagersModule extends MainModule {
	public static VillagerTypesFeature villagerTypes;
	public static VillagerProfessionsSubModule villagerProfessions;

	public VillagesAndVillagersModule() {
		super("villages-and-villagers", "Improves villages by adding new professions, biome variants and more!");

		villagerTypes = register(new VillagerTypesFeature());
		villagerProfessions = register(new VillagerProfessionsSubModule());
	}
}
