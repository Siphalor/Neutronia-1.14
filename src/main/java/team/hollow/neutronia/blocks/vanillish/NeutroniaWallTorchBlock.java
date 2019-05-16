package team.hollow.neutronia.blocks.vanillish;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;

public class NeutroniaWallTorchBlock extends WallTorchBlock {
	protected NeutroniaWallTorchBlock() {
		super(FabricBlockSettings.copy(Blocks.WALL_TORCH).build());
	}
}
