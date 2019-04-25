package team.hollow.module_api.api.features.woodtype;

import net.minecraft.block.Block;
import net.minecraft.util.Pair;
import team.hollow.neutronia.unsure.WoodType;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.util.Set;
import java.util.function.Supplier;

public class WoodTypeBlocksFeature extends WoodTypeFeature {


	private final String suffix;
	private Pair<String, Supplier<Block>>[] blocks;

	@SafeVarargs
	public WoodTypeBlocksFeature(String configName, String enablesDescription, Set<WoodType> skipWoodTypes, String suffix, Pair<String, Supplier<Block>>... blocks) {
		super(configName, enablesDescription, skipWoodTypes);
		this.suffix = suffix;
		this.blocks = blocks;
	}

	@Override
	protected void process(WoodType woodType) {
		for(Pair<String, Supplier<Block>> pair : blocks) {
			RegistryUtils.register(pair.getRight().get(), pair.getLeft() + "_" + woodType.getName() + "_" + suffix);
		}
	}
}
