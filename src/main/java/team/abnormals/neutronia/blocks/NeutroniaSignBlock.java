package team.abnormals.neutronia.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntegerProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import team.abnormals.neutronia.blocks.entity.SignBlockEntity;

public class NeutroniaSignBlock extends SignBlock implements INeutroniaBlock {

    public static final IntegerProperty ROTATION;

    static {
        ROTATION = Properties.ROTATION_16;
    }

    public NeutroniaSignBlock(String name) {
        super(Block.Settings.of(Material.WOOD));
        this.setDefaultState(this.stateFactory.getDefaultState().with(ROTATION, 0).with(WATERLOGGED, false));
        register(name);
    }

    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return viewableWorld_1.getBlockState(blockPos_1.down()).getMaterial().method_15799();
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        FluidState fluidState_1 = itemPlacementContext_1.getWorld().getFluidState(itemPlacementContext_1.getBlockPos());
        return this.getDefaultState().with(ROTATION, MathHelper.floor((double) ((180.0F + itemPlacementContext_1.getPlayerYaw()) * 16.0F / 360.0F) + 0.5D) & 15).with(WATERLOGGED, fluidState_1.getFluid() == Fluids.WATER);
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        return direction_1 == Direction.DOWN && !this.canPlaceAt(blockState_1, iWorld_1, blockPos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    public BlockState rotate(BlockState blockState_1, Rotation rotation_1) {
        return blockState_1.with(ROTATION, rotation_1.method_10502(blockState_1.get(ROTATION), 16));
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        if (world_1.isClient) {
            return true;
        } else {
            BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
            if (blockEntity_1 instanceof SignBlockEntity) {
                SignBlockEntity signBlockEntity_1 = (SignBlockEntity)blockEntity_1;
                ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
                if (itemStack_1.getItem() instanceof DyeItem) {
                    boolean boolean_1 = signBlockEntity_1.setTextColor(((DyeItem)itemStack_1.getItem()).getColor());
                    if (boolean_1 && !playerEntity_1.isCreative()) {
                        itemStack_1.subtractAmount(1);
                    }
                }

                return signBlockEntity_1.onActivate(playerEntity_1);
            } else {
                return false;
            }
        }
    }

    public BlockState mirror(BlockState blockState_1, Mirror mirror_1) {
        return blockState_1.with(ROTATION, mirror_1.method_10344(blockState_1.get(ROTATION), 16));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(ROTATION, WATERLOGGED);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView_1) {
        return new SignBlockEntity();
    }

    private void register(String name) {
        Registry.register(Registry.BLOCK, getPrefix() + name, this);
    }

}
