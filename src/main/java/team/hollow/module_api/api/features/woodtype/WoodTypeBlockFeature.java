package team.hollow.module_api.api.features.woodtype;

import net.minecraft.block.Block;
import team.hollow.neutronia.unsure.ContentBuilder;
import team.hollow.neutronia.unsure.WoodType;

import java.util.Set;
import java.util.function.Supplier;

public class WoodTypeBlockFeature extends WoodTypeFeature {
	protected final Supplier<Block> block;

	public WoodTypeBlockFeature(String name, String enablesDescription, Set<WoodType> skipWoodTypes, Supplier<Block> block) {
		super(name, enablesDescription, skipWoodTypes);
		this.block = block;
	}

	protected void process(WoodType woodType) {
		ContentBuilder.getInstance().newBlock(woodType.getName() + "_" + name, block.get());
	}
}
