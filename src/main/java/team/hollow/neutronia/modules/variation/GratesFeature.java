package team.hollow.neutronia.modules.variation;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import team.hollow.module_api.api.features.OptionalFeature;
import team.hollow.neutronia.registry.ContentBuilder;

public class GratesFeature extends OptionalFeature {
	public GratesFeature() {
		super("grates", "Adds grates as equivalent to iron trapdoors");
	}

	@Override
	protected void applyEnabled() {
		addGrate(Blocks.IRON_BLOCK, "iron");
		addGrate(Blocks.GOLD_BLOCK, "gold");
	}

	public void addGrate(Block baseBlock, String baseName) {
		ContentBuilder contentBuilder = ContentBuilder.getInstance();
		contentBuilder.setBaseBlock(baseBlock);
		contentBuilder.setBaseName(baseName);
		contentBuilder.grate();
	}
}
