package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.neutronia.Neutronia;

import java.util.Set;

public class FencesAndWallsFeature extends OptionalFeature {
	private Set<Block> wallBlocks = ImmutableSet.of(
		Blocks.CRACKED_STONE_BRICKS
	);
	private Set<Block> fenceBlocks = ImmutableSet.of();

	public FencesAndWallsFeature() {
		super("fences-and-walls", "Adds more fences, fence gates and walls");
	}

	@Override
	protected void applyEnabled() {
        ContentBuilder contentBuilder = Neutronia.getContentBuilder();
		wallBlocks.forEach(block -> {
			contentBuilder.asBaseBlock(block, Registry.BLOCK.getId(block));
            contentBuilder.wall();
		});
		fenceBlocks.forEach(block -> {
			contentBuilder.asBaseBlock(block, Registry.BLOCK.getId(block));
			contentBuilder.fence();
		});
	}
}
