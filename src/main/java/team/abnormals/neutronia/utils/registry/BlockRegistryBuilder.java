package team.abnormals.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import team.abnormals.neutronia.blocks.NeutroniaButtonBlock;
import team.abnormals.neutronia.blocks.NeutroniaPressurePlateBlockImpl;
import team.abnormals.neutronia.blocks.NeutroniaSlabBlock;
import team.abnormals.neutronia.blocks.NeutroniaWallBlock;

public class BlockRegistryBuilder {

    public static final BlockRegistryBuilder INSTANCE = new BlockRegistryBuilder();
    private static String modid;
    private static Block block;

    public static BlockRegistryBuilder getInstance(String id, Block blockIn) {
        modid = id;
        block = blockIn;
        return INSTANCE;
    }

    public BlockRegistryBuilder addSlab(String name) {
        new NeutroniaSlabBlock(name);
        return this;
    }

    public BlockRegistryBuilder addStair(String name) {
        new NeutroniaSlabBlock(name);
        return this;
    }

    public BlockRegistryBuilder addFence(String name) {
        new NeutroniaSlabBlock(name);
        return this;
    }

    public BlockRegistryBuilder addFenceGate(String name) {
        new NeutroniaSlabBlock(name);
        return this;
    }
    public BlockRegistryBuilder addWall(String name) {
        new NeutroniaWallBlock(name);
        return this;
    }
    public BlockRegistryBuilder addButton(String name, boolean wooden) {
        new NeutroniaButtonBlock(name, wooden);
        return this;
    }
    public BlockRegistryBuilder addPressurePlate(String name, PressurePlateBlock.Type type) {
        new NeutroniaPressurePlateBlockImpl(name, block.getMaterial(block.getDefaultState()), type);
        return this;
    }

}
