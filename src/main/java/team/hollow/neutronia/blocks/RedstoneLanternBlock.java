package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.*;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class RedstoneLanternBlock extends LanternBlock {

    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public static final double PIXEL_LENGTH = 1D / 16D;
    public static final VoxelShape LANTERN_NORTH_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 12.5D);
    public static final VoxelShape LANTERN_SOUTH_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 9.5D);
    public static final VoxelShape LANTERN_WEST_AABB = Block.createCuboidShape(PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 12.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_EAST_AABB = Block.createCuboidShape(PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 9.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_UP_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 0D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 9D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_DOWN_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);

    public RedstoneLanternBlock() {
        super(FabricBlockSettings.of(Material.METAL).hardness(3.5F).sounds(BlockSoundGroup.LANTERN).lightLevel(15).build());
        setDefaultState(stateFactory.getDefaultState().with(FACING, Direction.UP).with(HANGING, false).with(LIT, false));
    }

    protected static Direction method_16370(BlockState blockState_1) {
        return blockState_1.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    public int getLuminance(BlockState blockState_1) {
        return blockState_1.get(LIT) ? super.getLuminance(blockState_1) : 0;
    }

    public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
        super.onBlockAdded(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
    }

    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        Direction direction_1 = method_16370(blockState_1).getOpposite();
        BlockPos blockPos_2 = blockPos_1.offset(direction_1);
        BlockState blockState_2 = viewableWorld_1.getBlockState(blockPos_2);
        Block block_1 = blockState_2.getBlock();
        if (canConnect(block_1)) {
            return false;
        } else {
            boolean boolean_1 = Block.isFaceFullSquare(blockState_2.getCollisionShape(viewableWorld_1, blockPos_2), direction_1.getOpposite()) || block_1.matches(BlockTags.FENCES) || block_1.matches(BlockTags.WALLS);
            if (direction_1 == Direction.UP) {
                return block_1 == Blocks.HOPPER || boolean_1;
            } else {
                return !canConnect(block_1) && boolean_1;
            }
        }
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getFacing()).with(LIT, itemPlacementContext_1.getWorld().isReceivingRedstonePower(itemPlacementContext_1.getBlockPos())).with(HANGING, itemPlacementContext_1.getFacing() == Direction.UP);
    }

    @Override
    public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_1) {
        if (!world_1.isClient) {
            boolean lit = blockState_1.get(LIT);
            if (lit != world_1.isReceivingRedstonePower(blockPos_1)) {
                if (lit) {
                    world_1.getBlockTickScheduler().schedule(blockPos_1, this, 4);
                } else {
                    world_1.setBlockState(blockPos_1, blockState_1.cycle(LIT), 2);
                }
            }

        }
    }

    public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (!world_1.isClient) {
            if (blockState_1.get(LIT) && !world_1.isReceivingRedstonePower(blockPos_1)) {
                world_1.setBlockState(blockPos_1, blockState_1.cycle(LIT), 2);
            }

        }
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

    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext verticalEntityPosition_1) {
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
    public OffsetType getOffsetType() {
        if (getDefaultState().get(FACING) == Direction.UP) return OffsetType.XZ;
        else if (getDefaultState().get(FACING) == Direction.DOWN) return OffsetType.XYZ;
        else return OffsetType.NONE;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(FACING, HANGING, LIT);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}