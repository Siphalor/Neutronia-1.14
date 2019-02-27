package team.hollow.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

import java.util.Objects;

public class PalisadeBlock extends NeutroniaFenceBlock {

    public static final BooleanProperty POST = BooleanProperty.create("post");
    public static final BooleanProperty POST_TOP = BooleanProperty.create("post_top");

    public PalisadeBlock(String name, BlockState state) {
        super(name, state);
        setDefaultState(this.stateFactory.getDefaultState().with(POST, false).with(POST_TOP, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        BlockView blockView_1 = itemPlacementContext_1.getWorld();
        BlockPos blockPos_1 = itemPlacementContext_1.getBlockPos();
        FluidState fluidState_1 = itemPlacementContext_1.getWorld().getFluidState(itemPlacementContext_1.getBlockPos());
        BlockPos blockPos_2 = blockPos_1.north();
        BlockPos blockPos_3 = blockPos_1.east();
        BlockPos blockPos_4 = blockPos_1.south();
        BlockPos blockPos_5 = blockPos_1.west();
        BlockState blockState_1 = blockView_1.getBlockState(blockPos_2);
        BlockState blockState_2 = blockView_1.getBlockState(blockPos_3);
        BlockState blockState_3 = blockView_1.getBlockState(blockPos_4);
        BlockState blockState_4 = blockView_1.getBlockState(blockPos_5);
        return Objects.requireNonNull(super.getPlacementState(itemPlacementContext_1)).with(NORTH, this.method_10184(blockState_1, Block.isFaceFullSquare(blockState_1.getCollisionShape(blockView_1, blockPos_2), Direction.SOUTH), Direction.SOUTH)).with(EAST, this.method_10184(blockState_2, Block.isFaceFullSquare(blockState_2.getCollisionShape(blockView_1, blockPos_3), Direction.WEST), Direction.WEST)).with(SOUTH, this.method_10184(blockState_3, Block.isFaceFullSquare(blockState_3.getCollisionShape(blockView_1, blockPos_4), Direction.NORTH), Direction.NORTH)).with(WEST, this.method_10184(blockState_4, Block.isFaceFullSquare(blockState_4.getCollisionShape(blockView_1, blockPos_5), Direction.EAST), Direction.EAST)).with(WATERLOGGED, fluidState_1.getFluid() == Fluids.WATER);
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        if (blockState_1.get(WATERLOGGED)) {
            iWorld_1.getFluidTickScheduler().schedule(blockPos_1, Fluids.WATER, Fluids.WATER.getTickRate(iWorld_1));
        }

        return direction_1.getAxis().method_10180() == Direction.Type.HORIZONTAL ? blockState_1.with(FACING_PROPERTIES.get(direction_1), this.method_10184(blockState_2, Block.isFaceFullSquare(blockState_2.getCollisionShape(iWorld_1, blockPos_2), direction_1.getOpposite()), direction_1.getOpposite())) : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(POST, POST_TOP, NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }

}