package team.hollow.neutronia.modules.exploration.trees;

import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import team.hollow.neutronia.blocks.NeutroniaWaterloggedSaplingBlock;
import team.hollow.neutronia.registry.ContentBuilder;

public class WaterloggableTreeFeature extends TreeFeature {
	public Block underwaterSapling;

	public WaterloggableTreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, saplingGenerator);
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();
		underwaterSapling = ContentBuilder.getInstance().newBlock("underwater_" + name + "_sapling", new NeutroniaWaterloggedSaplingBlock(saplingGenerator));
	}
}
