package team.abnormals.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import team.abnormals.neutronia.blocks.*;

public class BlockRegistryBuilder {

    public static final BlockRegistryBuilder INSTANCE = new BlockRegistryBuilder();
    private static Block block;
    private static String name;

    public static BlockRegistryBuilder getInstance(String nameIn, Block blockIn) {
        name = nameIn;
        block = blockIn;
        return INSTANCE;
    }

    public BlockRegistryBuilder slab() {
        new NeutroniaSlabBlock(name);
        return this;
    }

    public BlockRegistryBuilder stair() {
        new NeutroniaStairBlock(block.getDefaultState(), name);
        return this;
    }

    public BlockRegistryBuilder fence() {
        new NeutroniaFenceBlock(name + "_fence", block.getDefaultState());
        return this;
    }

    public BlockRegistryBuilder fenceGate() {
        new NeutroniaFenceGateBlock(name + "_fence_gate");
        return this;
    }

    public BlockRegistryBuilder wall() {
        new NeutroniaWallBlock(name + "_wall", block.getDefaultState());
        return this;
    }

    public BlockRegistryBuilder button(boolean wooden) {
        new NeutroniaButtonBlock(name + "_button", wooden);
        return this;
    }

    public BlockRegistryBuilder pressurePlate(PressurePlateBlock.Type type) {
        new NeutroniaPressurePlateBlockImpl(name, block.getMaterial(block.getDefaultState()), type);
        return this;
    }

    public BlockRegistryBuilder door() {
        new NeutroniaDoorBlock(name);
        return this;
    }

    public BlockRegistryBuilder trapdoor() {
        new NeutroniaTrapdoorBlock(name + "_trapdoor");
        return this;
    }

}
