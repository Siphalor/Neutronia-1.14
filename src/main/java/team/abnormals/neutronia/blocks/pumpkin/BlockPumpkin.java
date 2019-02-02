package team.abnormals.neutronia.blocks.pumpkin;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import team.abnormals.neutronia.blocks.BlockModBase;
import team.abnormals.neutronia.blocks.IMinecraftBlock;

public class BlockPumpkin extends BlockModBase implements IMinecraftBlock {

    public BlockPumpkin(PumpkinHelper.FaceTypes types) {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).resistance(1.0F).sounds(BlockSoundGroup.WOOD).build(), "pumpkin" + types.ordinal());
    }

    public BlockPumpkin() {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).resistance(1.0F).sounds(BlockSoundGroup.WOOD).build(), "pumpkin");
        PumpkinHelper.init();
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        ItemStack stack = playerEntity_1.getStackInHand(hand_1);
        if(stack.getItem() == Items.SHEARS) {
            world_1.setBlockState(blockPos_1, PumpkinHelper.getNext(Registry.BLOCK.getId(this)));
        }
        return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
    }

}