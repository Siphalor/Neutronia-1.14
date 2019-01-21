package team.abnormals.neutronia.blocks;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Material;

public class BlockFrostedGlassPane extends BlockNeutroniaPane {

	public BlockFrostedGlassPane() {
		super(Settings.of(Material.GLASS).strength(3.0F, 10.0F), "frosted_glass_pane");
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}