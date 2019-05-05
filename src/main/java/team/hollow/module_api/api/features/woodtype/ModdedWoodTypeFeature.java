package team.hollow.module_api.api.features.woodtype;

import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.registry.WoodTypeRegistry;

public abstract class ModdedWoodTypeFeature extends OptionalFeature implements WoodTypeRegistry.ModdedTypeListener {
	public ModdedWoodTypeFeature(String name, String enablesDescription) {
		super(name, enablesDescription);

		WoodTypeRegistry.registerModdedTypeListener(this);
	}
}
