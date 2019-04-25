package team.hollow.neutronia.modules.decoration;

import net.minecraft.block.Block;
import team.hollow.module_api.api.OptionalFeature;
import team.hollow.neutronia.enums.WoodType;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.function.Supplier;

public class WoodTypeBlockFeature extends OptionalFeature {
	protected final Supplier<Block> block;

	public WoodTypeBlockFeature(String name, String plural, Supplier<Block> block) {
		super(name, "Enables loads of " + formatName(plural));
		this.block = block;
	}

	@Override
	protected void applyEnabled() {
		for(WoodType woodType : WoodType.VANILLA_WOODS) {
			if(woodType == WoodType.OAK)
				continue;
			RegistryUtils.register(block.get(), woodType.asString() + "_" + name);
		}
	}

	public void applyForModdedWood(String woodName) {
		if(isEnabled()) {
			RegistryUtils.register(block.get(), woodName + "_" + name);
		}
	}
}
