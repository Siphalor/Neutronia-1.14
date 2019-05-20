package team.hollow.neutronia.modules.features;

import net.minecraft.block.Block;
import net.minecraft.util.Pair;
import team.hollow.abnormalib.modules.api.features.woodtype.WoodTypeFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.registry.WoodType;
import team.hollow.neutronia.Neutronia;

import java.util.Set;
import java.util.function.Function;

public class WoodTypeBlocksFeature extends WoodTypeFeature {
	private final String suffix;
	private final Pair<String, Function<WoodType, Block>>[] blockFunctions;

	public WoodTypeBlocksFeature(String name, String enablesDescription, Set<WoodType> skipWoodTypes, String suffix, Pair<String, Function<WoodType, Block>>... blockFunctions) {
		super(name, enablesDescription, skipWoodTypes);
		this.suffix = suffix;
		this.blockFunctions = blockFunctions;
	}

	@Override
	protected void process(WoodType woodType) {
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		contentBuilder.asBaseBlock(woodType.baseBlock, woodType.getBaseBlockIdentifier());
		for(Pair<String, Function<WoodType, Block>> blockFunction : blockFunctions) {
			contentBuilder.newBlock(woodType.getIdentifier().getPath() + "_" + blockFunction.getLeft() + "_" + suffix, blockFunction.getRight().apply(woodType));
		}
	}
}
