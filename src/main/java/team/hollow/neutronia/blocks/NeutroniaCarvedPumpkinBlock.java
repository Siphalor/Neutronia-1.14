package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;

public class NeutroniaCarvedPumpkinBlock extends CarvedPumpkinBlock {
	public NeutroniaCarvedPumpkinBlock(boolean lit) {
		super(FabricBlockSettings.copy(Blocks.PUMPKIN).lightLevel(lit ? 15 : 0).build());
	}
}
