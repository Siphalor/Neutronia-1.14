package team.abnormals.neutronia.blocks.pumpkin;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.abnormals.neutronia.blocks.BlockModBase;
import team.abnormals.neutronia.blocks.INeutroniaBlock;

public class BlockJackOLantern extends BlockModBase implements INeutroniaBlock {

    public static final DirectionProperty FACING = HorizontalFacingBlock.field_11177;
    public static final EnumProperty<FaceTypes> FACE_TYPE = EnumProperty.create("face_type", FaceTypes.class);

    public BlockJackOLantern() {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).resistance(1.0F).sounds(BlockSoundGroup.WOOD).build(), "incricate_jack_o_lantern");
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH).with(FACE_TYPE, FaceTypes.CREEPER));
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        ItemStack stack = playerEntity_1.getStackInHand(hand_1);
        if(stack.getItem() == Items.SHEARS) {
            world_1.setBlockState(blockPos_1, blockState_1.method_11572(FACE_TYPE));
        }
        return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite());
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING, FACE_TYPE);
    }

    public enum FaceTypes implements StringRepresentable {
        CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
        OR, SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

        @Override
        public String asString() {
            return this.toString().toLowerCase();
        }

    }

}