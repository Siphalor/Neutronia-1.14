package team.hollow.neutronia.modules.features;

import net.minecraft.block.Block;
import team.hollow.abnormalib.modules.api.features.woodtype.WoodTypeFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.WoodType;
import team.hollow.neutronia.Neutronia;

import java.util.Set;
import java.util.function.Function;

public class WoodTypeBlockFeature extends WoodTypeFeature {
	private final Function<WoodType, Block> blockFunction;

	public WoodTypeBlockFeature(String name, String enablesDescription, Set<WoodType> skipWoodTypes, Function<WoodType, Block> blockFunction) {
		super(name, enablesDescription, skipWoodTypes);
		this.blockFunction = blockFunction;
	}

	@Override
	protected void process(WoodType woodType) {
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		contentBuilder.asBaseBlock(woodType.baseBlock, woodType.getBaseBlockIdentifier());
		contentBuilder.newBlock(woodType.getIdentifier().getPath() + "_" + name, blockFunction.apply(woodType));
	}
}
