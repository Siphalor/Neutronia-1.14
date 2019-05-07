package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.registry.ContentBuilder;

import java.util.Set;

public class StairsAndSlabsFeature extends OptionalFeature {
	Set<Block> baseBlocks = ImmutableSet.of();

	public StairsAndSlabsFeature() {
		super("stairs-and-slabs", "Adds more stairs and slabs");
	}

	@Override
	protected void applyEnabled() {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
		baseBlocks.forEach(block -> {
			contentBuilder.asBaseBlock(block);
			contentBuilder.stairs();
			contentBuilder.slab();
		});
	}
}
