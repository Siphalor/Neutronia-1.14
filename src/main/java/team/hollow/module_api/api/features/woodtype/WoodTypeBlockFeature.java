package team.hollow.module_api.api.features.woodtype;

import net.minecraft.block.Block;
import team.hollow.neutronia.unsure.ContentBuilder;
import team.hollow.neutronia.unsure.WoodType;

import java.util.Set;
import java.util.function.Function;

public class WoodTypeBlockFeature extends WoodTypeFeature {
	protected final Function<WoodType, Block> block;

	public WoodTypeBlockFeature(String name, String enablesDescription, Set<WoodType> skipWoodTypes, Function<WoodType, Block> block) {
		super(name, enablesDescription, skipWoodTypes);
		this.block = block;
	}

	protected void process(WoodType woodType) {
		ContentBuilder.getInstance().newBlock(woodType.getIdentifier().getPath() + "_" + name, block.apply(woodType));
	}
}
