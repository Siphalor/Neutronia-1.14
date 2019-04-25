package team.hollow.neutronia.unsure;

import net.minecraft.block.Block;

public class WoodType {
	protected String name;
	protected Block baseBlock;

	public WoodType(String name, Block baseBlock) {
		this.name = name;
		this.baseBlock = baseBlock;
	}

	public String getName() {
		return name;
	}

	public Block getBaseBlock() {
		return baseBlock;
	}
}
