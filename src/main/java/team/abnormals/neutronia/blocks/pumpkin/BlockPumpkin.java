package team.abnormals.neutronia.blocks.pumpkin;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.abnormals.neutronia.blocks.BlockModBase;
import team.abnormals.neutronia.blocks.INeutroniaBlock;

public class BlockPumpkin extends BlockModBase implements INeutroniaBlock {

    public static final EnumProperty<PumpkinHelper.FaceTypes> TYPE = EnumProperty.create("type", PumpkinHelper.FaceTypes.class);

    public static final EnumProperty<Direction> FACING = CarvedPumpkinBlock.FACING;

    public BlockPumpkin() {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).resistance(1.0F).sounds(BlockSoundGroup.WOOD).build(), "pumpkin");
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        ItemStack stack = playerEntity_1.getStackInHand(hand_1);
        if(stack.getItem() instanceof ShearsItem) {
            int addition = 1;
            if (playerEntity_1.isSneaking()) {
                addition = -1;
            }

            PumpkinHelper.FaceTypes oldType = blockState_1.get(TYPE);
            PumpkinHelper.FaceTypes newType = PumpkinHelper.FaceTypes.values()[oldType.ordinal() + addition];

            world_1.setBlockState(blockPos_1, blockState_1.with(TYPE, newType));
            return false;
        }
        return true;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(TYPE, FACING);
    }

}
