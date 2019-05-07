package team.hollow.neutronia.blocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.util.Identifier;

public class NeutroniaSimpleChestBlock extends ChestBlock {
	public final Identifier type;

	public NeutroniaSimpleChestBlock(Identifier type) {
		super(Settings.copy(Blocks.CHEST));
		this.type = type;
	}
}
