package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class NeutroniaBottomTopBlock extends NeutroniaBlock {
	public NeutroniaBottomTopBlock(Material material) {
		super(material);
	}

	public NeutroniaBottomTopBlock(Material material, float hardness, float resistant) {
		super(material, hardness, resistant);
	}

	public NeutroniaBottomTopBlock(FabricBlockSettings builder) {
		super(builder);
	}
}
