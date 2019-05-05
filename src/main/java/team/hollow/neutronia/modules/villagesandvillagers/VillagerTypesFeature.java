package team.hollow.neutronia.modules.villagesandvillagers;

import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biomes;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.village.VillagerTypeRegistry;

public class VillagerTypesFeature extends OptionalFeature {
	public VillagerType redMushroom;
	public VillagerType brownMushroom;
	public VillagerType mountains;
	public VillagerType coldMountains;
	public VillagerType badlands;
	public VillagerType iceSpikes;
	public VillagerType ocean;

	public VillagerTypesFeature() {
		super("biome-variation", "Adds villagers types for more biomes");
	}

	@Override
	protected void applyEnabled() {
		redMushroom = VillagerTypeRegistry.register("red_mushroom", Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE);
		brownMushroom = VillagerTypeRegistry.register("brown_mushroom", Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE);
		mountains = VillagerTypeRegistry.register("mountains", Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.WOODED_MOUNTAINS);
		coldMountains = VillagerTypeRegistry.register("cold_mountains", Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS);
		badlands = VillagerTypeRegistry.register("badlands", Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU);
		iceSpikes = VillagerTypeRegistry.register("ice_spikes", Biomes.ICE_SPIKES);
		ocean = VillagerTypeRegistry.register("ocean", Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);
	}
}
