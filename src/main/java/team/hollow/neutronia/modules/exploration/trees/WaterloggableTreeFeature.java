package team.hollow.neutronia.modules.exploration.trees;

import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.NeutroniaWaterloggedSaplingBlock;

public class WaterloggableTreeFeature extends TreeFeature {
	public Block underwaterSapling;

	public WaterloggableTreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, saplingGenerator);
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();
		underwaterSapling = Neutronia.getContentBuilder().newBlock("underwater_" + name + "_sapling", new NeutroniaWaterloggedSaplingBlock(saplingGenerator));
	}
}
