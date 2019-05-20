package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.neutronia.Neutronia;

import java.util.Set;

public class StairsAndSlabsFeature extends OptionalFeature {
	Set<Block> baseBlocks = ImmutableSet.of();

	public StairsAndSlabsFeature() {
		super("stairs-and-slabs", "Adds more stairs and slabs");
	}

	@Override
	protected void applyEnabled() {
		ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		baseBlocks.forEach(block -> {
			contentBuilder.asBaseBlock(block);
			contentBuilder.stairs();
			contentBuilder.slab();
		});
	}
}
