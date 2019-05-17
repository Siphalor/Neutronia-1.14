package team.hollow.neutronia.modules.features;

import net.minecraft.block.Block;
import team.hollow.abnormalib.modules.api.features.OptionalFeature;
import team.hollow.neutronia.Neutronia;

public class SingleBlockFeature extends OptionalFeature {
	private final Block block;

	public SingleBlockFeature(Block block, String name, String enablesDescription) {
		super(name, enablesDescription);
		this.block = block;
	}

	@Override
	protected void applyEnabled() {
		Neutronia.getContentBuilder().newBlock(name, block);
	}
}
