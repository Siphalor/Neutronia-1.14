package team.hollow.neutronia.modules.variation.wood;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.unsure.WoodTypeRegistry;

public class BambooWoodFeature extends OptionalFeature {
	public WoodType woodType;

	public BambooWoodFeature() {
		super("bamboo-as-wood", "Gives you the ability to craft a special kind of wood out of bamboo");
	}

	@Override
	protected void applyEnabled() {
		woodType = WoodTypeRegistry.registerModded(new WoodType(new Identifier(Neutronia.MOD_ID, "bamboo")));
	}
}
