package team.hollow.neutronia.modules.variation;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.module_api.api.features.woodtype.WoodTypeFeature;
import team.hollow.neutronia.registry.ContentBuilder;
import team.hollow.neutronia.registry.WoodType;

import java.util.Collections;
import java.util.Set;

public class SidingsFeature extends WoodTypeFeature {
	Set<Block> sidingBlocks = ImmutableSet.of(
	);

	public SidingsFeature() {
		super("sidings", "Adds sideways slabs and corners", Collections.emptySet());
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();
		sidingBlocks.forEach(block -> process(block, Registry.BLOCK.getId(block)));
	}

	@Override
	protected void process(WoodType woodType) {
        process(woodType.baseBlock, woodType.getBaseBlockIdentifier());
	}

	protected void process(Block baseBlock, Identifier baseBlockIdentifier) {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
        contentBuilder.asBaseBlock(baseBlock, baseBlockIdentifier);
		contentBuilder.siding();
	}
}
