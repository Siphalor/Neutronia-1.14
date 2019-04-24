package team.hollow.neutronia.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

import java.util.Map;

public class NeutroniaWallSignBlock extends SignBlock {
    public static final DirectionProperty FACING;
    private static final Map<Direction, VoxelShape> FACING_TO_SHAPE;

    static {
        FACING = HorizontalFacingBlock.FACING;
        FACING_TO_SHAPE = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(0.0D, 4.5D, 14.0D, 16.0D, 12.5D, 16.0D), Direction.SOUTH, Block.createCuboidShape(0.0D, 4.5D, 0.0D, 16.0D, 12.5D, 2.0D), Direction.EAST, Block.createCuboidShape(0.0D, 4.5D, 0.0D, 2.0D, 12.5D, 16.0D), Direction.WEST, Block.createCuboidShape(14.0D, 4.5D, 0.0D, 16.0D, 12.5D, 16.0D)));
    }

    public NeutroniaWallSignBlock() {
        super(FabricBlockSettings.of(Material.WOOD).build());
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView_1) {
        return new SignBlockEntity();
    }

    public String getTranslationKey() {
        return this.getItem().getTranslationKey();
    }

    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return FACING_TO_SHAPE.get(blockState_1.get(FACING));
    }

    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return viewableWorld_1.getBlockState(blockPos_1.offset(blockState_1.get(FACING).getOpposite())).getMaterial().isReplaceable();
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        BlockState blockState_1 = this.getDefaultState();
        FluidState fluidState_1 = itemPlacementContext_1.getWorld().getFluidState(itemPlacementContext_1.getBlockPos());
        ViewableWorld viewableWorld_1 = itemPlacementContext_1.getWorld();
        BlockPos blockPos_1 = itemPlacementContext_1.getBlockPos();
        Direction[] directions_1 = itemPlacementContext_1.getPlacementFacings();
        Direction[] var7 = directions_1;
        int var8 = directions_1.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            Direction direction_1 = var7[var9];
            if (direction_1.getAxis().isHorizontal()) {
                Direction direction_2 = direction_1.getOpposite();
                blockState_1 = blockState_1.with(FACING, direction_2);
                if (blockState_1.canPlaceAt(viewableWorld_1, blockPos_1)) {
                    return blockState_1.with(WATERLOGGED, fluidState_1.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        return direction_1.getOpposite() == blockState_1.get(FACING) && !blockState_1.canPlaceAt(iWorld_1, blockPos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    public BlockState rotate(BlockState blockState_1, BlockRotation rotation_1) {
        return blockState_1.with(FACING, rotation_1.rotate(blockState_1.get(FACING)));
    }

    public BlockState mirror(BlockState blockState_1, BlockMirror mirror_1) {
        return blockState_1.rotate(mirror_1.getRotation(blockState_1.get(FACING)));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING, WATERLOGGED);
    }

}
