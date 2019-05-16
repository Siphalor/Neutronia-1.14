package team.hollow.neutronia.blocks.vanillish;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import team.hollow.neutronia.modules.exploration.food.BerriesFeature;
import team.hollow.neutronia.registry.NoBlockItem;

public class NeutroniaBerryBushBlock extends SweetBerryBushBlock implements NoBlockItem {
    private final BerriesFeature.BerryType berryType;

    public NeutroniaBerryBushBlock(BerriesFeature.BerryType berryType) {
        super(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).build());
        this.berryType = berryType;
        this.setDefaultState(this.stateFactory.getDefaultState().with(AGE, 0));
    }

    @Override
    public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
        return new ItemStack(BerriesFeature.getBerries(berryType));
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        int int_1 = blockState_1.get(AGE);
        boolean boolean_1 = int_1 == 3;
        if (!boolean_1 && playerEntity_1.getStackInHand(hand_1).getItem() == Items.BONE_MEAL) {
            return false;
        } else if (int_1 > 1) {
            int int_2 = 1 + world_1.random.nextInt(2);
            dropStack(world_1, blockPos_1, new ItemStack(BerriesFeature.getBerries(berryType), int_2 + (boolean_1 ? 1 : 0)));
            world_1.playSound(null, blockPos_1, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world_1.random.nextFloat() * 0.4F);
            world_1.setBlockState(blockPos_1, blockState_1.with(AGE, 1), 2);
            return true;
        } else {
            return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
        }
    }
}