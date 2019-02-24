package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.abnormals.neutronia.INeutroniaInfo;

public class ChiseledBlock extends BaseModBlock implements INeutroniaInfo {

    public static final BooleanProperty FILLED = BooleanProperty.create("filled");
    protected Item chiselItem;

    public ChiseledBlock(Material material, String name, float hardness, float resistance, Item chiselItem) {
        super(Block.Settings.of(material).strength(hardness, resistance), name);
        setDefaultState(this.getDefaultState().with(FILLED, false));
        this.chiselItem = chiselItem;
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        Item heldItem = playerEntity_1.getStackInHand(hand_1).getItem();

        if (heldItem == chiselItem && !blockState_1.get(FILLED)) {
            world_1.setBlockState(blockPos_1, this.getDefaultState().with(FILLED, true));
            playerEntity_1.getStackInHand(hand_1).addAmount(playerEntity_1.getStackInHand(hand_1).getAmount() - 1);
            return true;
        }
        if (blockState_1.get(FILLED)) {
            if (playerEntity_1.getStackInHand(hand_1).isEmpty()) {
                world_1.setBlockState(blockPos_1, this.getDefaultState().with(FILLED, false));
                playerEntity_1.setStackInHand(hand_1, new ItemStack(chiselItem));
            }
            if (heldItem == chiselItem) {
                world_1.setBlockState(blockPos_1, this.getDefaultState().with(FILLED, false));
                playerEntity_1.getStackInHand(hand_1).setAmount(playerEntity_1.getStackInHand(hand_1).getAmount() + 1);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FILLED);
    }

    @Override
    public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
        return new ItemStack(this.getDefaultState().getBlock());
    }

}
