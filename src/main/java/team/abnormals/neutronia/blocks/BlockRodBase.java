package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;

public class BlockRodBase extends BlockDirectional implements INeutroniaBlock {

    protected static final VoxelShape BB_AXIS_Y = Block.createCubeShape(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    protected static final VoxelShape BB_AXIS_Z = Block.createCubeShape(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
    protected static final VoxelShape BB_AXIS_X = Block.createCubeShape(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);

    @SuppressWarnings("unused")
    public BlockRodBase(String name, boolean emitsLight) {
        super(emitsLight ? FabricBlockSettings.of(Material.STONE).strength(0.3F, 1.0F).lightLevel(13) : FabricBlockSettings.of(Material.STONE), name);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.UP));
    }

    @SuppressWarnings("unused")
    public BlockRodBase(Material material, String name, boolean emitsLight) {
        super(emitsLight ? FabricBlockSettings.of(material).hardness(0.3F).lightLevel(13) : FabricBlockSettings.of(material), name);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    public BlockState applyRotation(BlockState blockState_1, Rotation rotation_1) {
        return blockState_1.with(FACING, rotation_1.method_10503(blockState_1.get(FACING)));
    }

    @Override
    public BlockState applyMirror(BlockState blockState_1, Mirror mirror_1) {
        return blockState_1.with(FACING, mirror_1.apply(blockState_1.get(FACING)));
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        switch (blockState_1.get(FACING).getAxis()) {
            case X:
            default:
                return BB_AXIS_X;
            case Z:
                return BB_AXIS_Z;
            case Y:
                return BB_AXIS_Y;
        }
    }

    @Override
    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        BlockState iblockstate = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getPos().offset(itemPlacementContext_1.getPlayerFacing().getOpposite()));

        if (iblockstate.getBlock() == this) {
            Direction enumfacing = iblockstate.get(FACING);

            if (enumfacing == itemPlacementContext_1.getFacing()) {
                return this.getDefaultState().with(FACING, itemPlacementContext_1.getFacing().getOpposite());
            }
        }

        return this.getDefaultState().with(FACING, itemPlacementContext_1.getFacing());
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING);
    }

}