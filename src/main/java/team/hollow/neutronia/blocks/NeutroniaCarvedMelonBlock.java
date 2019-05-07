package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.math.Direction;

public class NeutroniaCarvedMelonBlock extends HorizontalFacingBlock {
	public NeutroniaCarvedMelonBlock(boolean lit) {
		super(FabricBlockSettings.copy(Blocks.MELON).lightLevel(lit ? 15 : 0).build());
		setDefaultState(getStateFactory().getDefaultState().with(HorizontalFacingBlock.FACING, Direction.NORTH));
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(HorizontalFacingBlock.FACING, itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		stateFactory$Builder_1.add(HorizontalFacingBlock.FACING);
	}
}
