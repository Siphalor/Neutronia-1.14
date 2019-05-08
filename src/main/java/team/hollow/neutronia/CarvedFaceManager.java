package team.hollow.neutronia;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarvedFaceManager {

    private static List<Set<BlockCarved>> carvedVariants = new ArrayList<>();

    private CarvedFaceManager() {

    }

    public static BlockCarved next(BlockCarved current) {
        Set<BlockCarved> carvedBlocks = carvedVariants.stream().filter(s -> s.contains(current)).findAny().orElse(new HashSet<>());
        if (carvedBlocks.isEmpty()) {
            return current;
        }
        int ordinal = current.type.ordinal();
        ordinal += 1;
        if (ordinal >= CarvedFaceTypes.values().length) {
            ordinal = 0;
        }
        CarvedFaceTypes nextType = CarvedFaceTypes.values()[ordinal];
        return carvedBlocks.stream().filter(b -> b.type == nextType).findAny().orElse(current);
    }

    public static BlockCarved previous(BlockCarved current) {
        Set<BlockCarved> carvedBlocks = carvedVariants.stream().filter(s -> s.contains(current)).findAny().orElse(new HashSet<>());
        if (carvedBlocks.isEmpty()) {
            return current;
        }
        int ordinal = current.type.ordinal();
        ordinal -= 1;
        if (ordinal < 0) {
            ordinal = CarvedFaceTypes.values().length - 1;
        }
        CarvedFaceTypes nextType = CarvedFaceTypes.values()[ordinal];
        return carvedBlocks.stream().filter(b -> b.type == nextType).findAny().orElse(current);
    }

    public static Block[] forRegistry(String name) {
        Set<BlockCarved> blocks = new HashSet<>();
        for (CarvedFaceTypes type : CarvedFaceTypes.values()) {
            blocks.add(new BlockCarved(name, type));
        }
        carvedVariants.add(blocks);
        return blocks.toArray(new Block[0]);
    }

    public static Block[] forRegistry(String name, Identifier toReplace) {
        Set<BlockCarved> blocks = new HashSet<>();
        for (CarvedFaceTypes type : CarvedFaceTypes.values()) {
            blocks.add(new BlockCarved(name, type, toReplace));
        }
        carvedVariants.add(blocks);
        return blocks.toArray(new Block[0]);
    }

    public enum CarvedFaceTypes {
        CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
        SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK, NONE;

        public String asString() {
            return this.toString().toLowerCase();
        }

    }

    public static class BlockCarved extends Block {

        public static final DirectionProperty facing = DirectionProperty.create("facing");
        public String name;
        public Identifier replace;
        CarvedFaceTypes type;

        public BlockCarved(String name, CarvedFaceTypes type) {
            super(Block.Settings.copy(Blocks.CARVED_PUMPKIN));
            this.type = type;
            this.name = "carved_" + type.asString() + "_" + name;
            this.replace = null;
        }

        public BlockCarved(String name, CarvedFaceTypes type, Identifier replace) {
            super(Block.Settings.copy(Blocks.CARVED_PUMPKIN));
            this.type = type;
            this.name = "carved_" + type.asString() + "_" + name;
            this.replace = replace;
        }

        @Override
        public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
            if (world_1.isClient) {
                return true;
            }
            ItemStack held = playerEntity_1.getStackInHand(hand_1);
            if (held.isEmpty() || held.getItem() != Items.SHEARS) {
                return true;
            }
            BlockCarved toReplaceWith;
            if (playerEntity_1.isSneaking()) {
                toReplaceWith = next(this);
            } else {
                toReplaceWith = previous(this);
            }
            world_1.setBlockState(blockPos_1, toReplaceWith.getDefaultState());
            return false;
        }

        @Override
        protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
            super.appendProperties(stateFactory$Builder_1);
            stateFactory$Builder_1.add(facing);
        }

    }
}