package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LanternBlock extends net.minecraft.block.LanternBlock {

    public static final DirectionProperty FACING = DirectionProperty.create("facing");

    public static final double PIXEL_LENGTH = 1D / 16D;

    public static final VoxelShape LANTERN_NORTH_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 12.5D);
    public static final VoxelShape LANTERN_SOUTH_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 9.5D);
    public static final VoxelShape LANTERN_WEST_AABB = Block.createCuboidShape(PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 12.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_EAST_AABB = Block.createCuboidShape(PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 9.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_UP_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 0D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 9D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_DOWN_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);

    public LanternBlock() {
        super(FabricBlockSettings.of(Material.METAL).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build());
        setDefaultState(stateFactory.getDefaultState().with(FACING, Direction.UP).with(HANGING, false));
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        Vec3d offset = getOffsetPos(blockState_1, blockView_1, blockPos_1);
        switch (blockState_1.get(FACING)) {
            case NORTH:
                return LANTERN_NORTH_AABB;
            case SOUTH:
                return LANTERN_SOUTH_AABB;
            case EAST:
                return LANTERN_EAST_AABB;
            case WEST:
                return LANTERN_WEST_AABB;
            case UP:
                return LANTERN_UP_AABB.offset(offset.x, offset.y, offset.z);
            case DOWN:
                return LANTERN_DOWN_AABB.offset(offset.x, offset.y, offset.z);
            default:
                return LANTERN_UP_AABB;
        }
    }

    @Override
    public Vec3d getOffsetPos(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        if (blockState_1.get(FACING) == Direction.UP) {
            long i = MathHelper.hashCode(blockPos_1.getX(), 0, blockPos_1.getZ());
            return new Vec3d(((double) ((float) (i >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D, 0.0D, ((double) ((float) (i >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D);
        } else if (blockState_1.get(FACING) == Direction.DOWN) {
            long i = MathHelper.hashCode(blockPos_1.getX(), 0, blockPos_1.getZ());
            return new Vec3d(((double) ((float) (i >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D, ((double) ((float) (i >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D, ((double) ((float) (i >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D);
        } else {
            return Vec3d.ZERO;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getFacing()).with(HANGING, itemPlacementContext_1.getFacing() == Direction.DOWN || itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos().up()).getBlock() instanceof ChainBlock);
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1 = new StateFactory.Builder<>(this);
        super.appendProperties(stateFactory$Builder_1);
        stateFactory$Builder_1.with(FACING);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.MIPPED_CUTOUT;
    }

}