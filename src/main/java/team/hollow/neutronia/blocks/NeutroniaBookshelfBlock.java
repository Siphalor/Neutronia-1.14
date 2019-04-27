package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class NeutroniaBookshelfBlock extends NeutroniaBlock {
	public NeutroniaBookshelfBlock(Material material) {
		super(material);
	}

	public NeutroniaBookshelfBlock(Material material, float hardness, float resistant) {
		super(material, hardness, resistant);
	}

	public NeutroniaBookshelfBlock(FabricBlockSettings builder) {
		super(builder);
	}
}
