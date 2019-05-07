package team.hollow.neutronia.modules.exploration.trees;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.sapling.SaplingGenerator;
import team.hollow.neutronia.blocks.NeutroniaBlock;

public class PalmTreeFeature extends TreeFeature {
	// TODO: Add coconut
	public Block topLog;

	public PalmTreeFeature(String name, SaplingGenerator saplingGenerator) {
		super(name, saplingGenerator);
	}

	@Override
	protected void applyEnabled() {
		super.applyEnabled();

        topLog = new NeutroniaBlock(Material.WOOD);
	}
}