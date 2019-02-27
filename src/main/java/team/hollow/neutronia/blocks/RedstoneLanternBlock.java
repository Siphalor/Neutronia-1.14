package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.block.BlockItem;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.hollow.neutronia.INeutroniaInfo;

import java.util.Random;

public class RedstoneLanternBlock extends LanternBlock implements INeutroniaInfo {

    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public static final double PIXEL_LENGTH = 1D / 16D;
    public static final VoxelShape LANTERN_NORTH_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 12.5D);
    public static final VoxelShape LANTERN_SOUTH_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 9.5D);
    public static final VoxelShape LANTERN_WEST_AABB = Block.createCuboidShape(PIXEL_LENGTH * 6.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 12.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_EAST_AABB = Block.createCuboidShape(PIXEL_LENGTH * 3.5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 9.5D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_UP_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 0D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 9D, PIXEL_LENGTH * 11D);
    public static final VoxelShape LANTERN_DOWN_AABB = Block.createCuboidShape(PIXEL_LENGTH * 5D, PIXEL_LENGTH * 1D, PIXEL_LENGTH * 5D, PIXEL_LENGTH * 11D, PIXEL_LENGTH * 10D, PIXEL_LENGTH * 11D);
    public BlockState onBlock, offBlock;

    public RedstoneLanternBlock(String name) {
        super(FabricBlockSettings.of(Material.METAL).build());
        setDefaultState(stateFactory.getDefaultState().with(FACING, Direction.UP).with(HANGING, Boolean.FALSE).with(LIT, false));
        register(getDefaultState().get(LIT) ? "lit_" + name + "_redstone_lantern" : name + "_redstone_lantern", this, ItemGroup.REDSTONE);
    }

    public RedstoneLanternBlock() {
        super(FabricBlockSettings.of(Material.METAL).build());
        setDefaultState(stateFactory.getDefaultState().with(FACING, Direction.UP).with(HANGING, false).with(LIT, false));
        register(getDefaultState().get(LIT) ? "lit_redstone_lantern" : "redstone_lantern", this, ItemGroup.REDSTONE);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

    public void setOnBlock(BlockState onBlock) {
        this.onBlock = onBlock;
    }

    @Override
    public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2) {
        if (!world_1.isClient) {
            if (getDefaultState().get(LIT) && !world_1.isReceivingRedstonePower(blockPos_1)) {
                world_1.setBlockState(blockPos_1, offBlock, 2);
            } else if (!getDefaultState().get(LIT) && world_1.isReceivingRedstonePower(blockPos_1)) {
                world_1.setBlockState(blockPos_1, onBlock, 2);
            }
        }
    }

    public void setOffBlock(BlockState offBlock) {
        this.offBlock = offBlock;
    }

    @Override
    public void onRandomTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (world_1.isClient) {
            if (getDefaultState().get(LIT) && !world_1.isReceivingRedstonePower(blockPos_1)) {
                world_1.setBlockState(blockPos_1, offBlock, 2);
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

    @Override
    public OffsetType getOffsetType() {
        if (getDefaultState().get(FACING) == Direction.UP) return OffsetType.XZ;
        else if (getDefaultState().get(FACING) == Direction.DOWN) return OffsetType.XYZ;
        else return OffsetType.NONE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getFacing()).with(LIT, itemPlacementContext_1.getWorld().isReceivingRedstonePower(itemPlacementContext_1.getBlockPos())).with(HANGING, itemPlacementContext_1.getFacing() == Direction.UP);
    }

    public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2) {
        if (!world_1.isClient) {
            boolean boolean_1 = blockState_1.get(LIT);
            if (boolean_1 != world_1.isReceivingRedstonePower(blockPos_1)) {
                if (boolean_1) {
                    world_1.getBlockTickScheduler().schedule(blockPos_1, this, 4);
                } else {
                    world_1.setBlockState(blockPos_1, blockState_1.method_11572(LIT), 2);
                }
            }
        }
    }

    public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (!world_1.isClient) {
            if (blockState_1.get(LIT) && !world_1.isReceivingRedstonePower(blockPos_1)) {
                world_1.setBlockState(blockPos_1, blockState_1.method_11572(LIT), 2);
            }
        }
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING, HANGING, LIT);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.MIPPED_CUTOUT;
    }

}