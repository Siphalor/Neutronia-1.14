package team.hollow.neutronia.modules.variation.wood;

import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.blocks.CustomChestBlock;
import team.hollow.neutronia.enums.CustomChestType;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class ChestFeature extends OptionalFeature {
	public ChestFeature() {
		super("chests", "Enables loads of variants for chests");
	}

	@Override
	protected void applyEnabled() {
        for(CustomChestType customChestType : CustomChestType.values()) {
			RegistryUtils.register(new CustomChestBlock(customChestType.asString()), customChestType.asString());
		}
	}

	public void applyForModdedWood(String woodName) {
		if(isEnabled()) {
			RegistryUtils.register(new CustomChestBlock(woodName), woodName);
		}
	}
}
