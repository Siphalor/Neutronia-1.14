package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.network.ClientDummyContainerProvider;
import net.minecraft.container.BlockContext;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.container.StonecutterContainer;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.hollow.abnormalib.blocks.BaseModBlock;

public class IceSawBlock extends BaseModBlock {
    private static final TranslatableTextComponent CONTAINER_NAME = new TranslatableTextComponent("container.neutronia.ice_saw");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public IceSawBlock() {
        super(FabricBlockSettings.of(Material.PACKED_ICE).friction(0.98F).hardness(0.5F).sounds(BlockSoundGroup.GLASS));
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH));
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite());
    }

    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        playerEntity_1.openContainer(blockState_1.createContainerProvider(world_1, blockPos_1));
        playerEntity_1.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
        return true;
    }

    public NameableContainerProvider createContainerProvider(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
        return new ClientDummyContainerProvider((int_1, playerInventory_1, playerEntity_1) ->
                new StonecutterContainer(int_1, playerInventory_1, BlockContext.create(world_1, blockPos_1)), CONTAINER_NAME);
    }

    public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return SHAPE;
    }

    public boolean method_9526(BlockState blockState_1) {
        return true;
    }

    public boolean isFullBoundsCubeForCulling(BlockState blockState_1) {
        return true;
    }

    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return blockState_1.with(FACING, blockRotation_1.rotate(blockState_1.get(FACING)));
    }

    public BlockState mirror(BlockState blockState_1, BlockMirror blockMirror_1) {
        return blockState_1.rotate(blockMirror_1.getRotation(blockState_1.get(FACING)));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING);
    }

    public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, BlockPlacementEnvironment blockPlacementEnvironment_1) {
        return false;
    }

}
