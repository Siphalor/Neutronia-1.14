package team.hollow.neutronia.modules.variation.wood;

import net.minecraft.util.Identifier;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.registry.WoodType;
import team.hollow.abnormalib.utils.registry.WoodTypeRegistry;
import team.hollow.neutronia.Neutronia;

public class BambooWoodFeature extends OptionalFeature {
	public WoodType woodType;

	public BambooWoodFeature() {
		super("bamboo-as-wood", "Gives you the ability to craft a special kind of wood out of bamboo");
	}

	@Override
	protected void applyEnabled() {
		woodType = WoodTypeRegistry.registerModded(new WoodType(new Identifier(Neutronia.MOD_ID, "bamboo")), 2.0F, 3.0F);
	}
}
