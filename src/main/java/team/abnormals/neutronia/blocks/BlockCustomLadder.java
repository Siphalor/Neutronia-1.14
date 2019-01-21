package team.abnormals.neutronia.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockCustomLadder extends BlockModBase implements INeutroniaBlock {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public BlockCustomLadder(String variant) {
        super(Material.PART, variant + "_ladder");
        setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    public BlockCustomLadder(String variant, Material material) {
        super(material, variant + "_ladder");
        setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return Blocks.LADDER.getOutlineShape(blockState_1, blockView_1, blockPos_1, verticalEntityPosition_1);
    }

    @Override
    public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, BlockPlacementEnvironment blockPlacementEnvironment_1) {
        return canBlockStay(blockView_1, blockPos_1);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        if (itemPlacementContext_1.getFacing().getAxis() != Direction.Axis.Y)
            return getDefaultState().with(FACING, itemPlacementContext_1.getFacing());

        BlockState stateUp = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getPos().up());
        if (stateUp.getBlock() == this)
            return getDefaultState().with(FACING, stateUp.get(FACING));

        return getDefaultState();
    }

    @Override
    public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2) {
        if (!canBlockStay(world_1, blockPos_1)) {
            dropStack(world_1, blockPos_1, new ItemStack(this));
            world_1.setBlockState(blockPos_1, Blocks.AIR.getDefaultState());
        }

        super.neighborUpdate(blockState_1, world_1, blockPos_1, block_1, blockPos_2);
    }

    protected boolean canBlockStay(BlockView worldIn, BlockPos pos) {
        Direction facing = worldIn.getBlockState(pos).get(FACING);
        return canBlockStay(worldIn, pos, facing);
    }

    protected boolean canBlockStay(BlockView worldIn, BlockPos pos, Direction facing) {
        boolean solid = facing.getAxis() != Direction.Axis.Y && worldIn.getBlockState(pos.offset(facing.getOpposite())).isSimpleFullBlock(worldIn, pos.offset(facing.getOpposite()));
        BlockState topState = worldIn.getBlockState(pos.up());
        return solid || (topState.getBlock() == this && (facing.getAxis() == Direction.Axis.Y || topState.get(FACING) == facing));
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockState applyRotation(BlockState blockState_1, Rotation rotation_1) {
        return blockState_1.with(FACING, rotation_1.method_10503(blockState_1.get(FACING)));
    }

    @Override
    public BlockState applyMirror(BlockState blockState_1, Mirror mirror_1) {
        return blockState_1.applyRotation(mirror_1.getRotation(blockState_1.get(FACING)));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING);
    }

}