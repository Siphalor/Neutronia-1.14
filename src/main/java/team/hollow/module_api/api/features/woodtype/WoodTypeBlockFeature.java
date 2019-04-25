package team.hollow.module_api.api.features.woodtype;

import net.minecraft.block.Block;
import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.Set;
import java.util.function.Supplier;

public class WoodTypeBlockFeature extends WoodTypeFeature {
	protected final Supplier<Block> block;

	public WoodTypeBlockFeature(String name, String enablesDescription, Set<WoodType> skipWoodTypes, Supplier<Block> block) {
		super(name, enablesDescription, skipWoodTypes);
		this.block = block;
	}

	protected void process(WoodType woodType) {
		RegistryUtils.register(block.get(),  woodType.getName() + "_" + name);
	}
}
