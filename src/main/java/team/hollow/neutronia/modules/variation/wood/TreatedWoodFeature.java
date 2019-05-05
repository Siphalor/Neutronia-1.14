package team.hollow.neutronia.modules.variation.wood;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.registry.WoodType;
import team.hollow.neutronia.registry.WoodTypeRegistry;

public class TreatedWoodFeature extends OptionalFeature {
	public WoodType woodType;

	public TreatedWoodFeature() {
		super("treated-wood", "Adds treated wood");
	}

	@Override
	protected void applyEnabled() {
		woodType = WoodTypeRegistry.registerModded(new WoodType(new Identifier(Neutronia.MOD_ID, "treated_wood")), 2.0F, 3.0F);
	}
}
