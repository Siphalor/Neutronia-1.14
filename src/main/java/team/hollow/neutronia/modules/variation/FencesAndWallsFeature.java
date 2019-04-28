package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.unsure.ContentBuilder;

import java.util.Set;

public class FencesAndWallsFeature extends OptionalFeature {
	private Set<Block> wallBlocks = ImmutableSet.of(

	);
	private Set<Block> fenceBlocks = ImmutableSet.of();

	public FencesAndWallsFeature() {
		super("fences-and-walls", "Adds more fences, fence gates and walls");
	}

	@Override
	protected void applyEnabled() {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
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
