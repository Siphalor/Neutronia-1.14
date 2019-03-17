package team.hollow.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import team.hollow.neutronia.blocks.*;

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
        RegistryUtils.register(new NeutroniaSlabBlock(), name + "_slab", ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public BlockRegistryBuilder stair() {
        RegistryUtils.register(new NeutroniaStairBlock(block.getDefaultState()), name + "_stairs", ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public BlockRegistryBuilder fence() {
        RegistryUtils.register(new NeutroniaFenceBlock(block.getDefaultState()), name + "_fence");
        return this;
    }

    public BlockRegistryBuilder fenceGate() {
        RegistryUtils.register(new NeutroniaFenceGateBlock(), name + "_fence_gate", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder wall() {
        RegistryUtils.register(new NeutroniaWallBlock(block.getDefaultState()), name + "_wall", ItemGroup.DECORATIONS);
        return this;
    }

    public BlockRegistryBuilder button(boolean wooden) {
        RegistryUtils.register(new NeutroniaButtonBlock(wooden), name + "_button", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder pressurePlate(PressurePlateBlock.Type type) {
        RegistryUtils.register(new NeutroniaPressurePlateBlock(block.getMaterial(block.getDefaultState()), type), name + "_pressure_plate", ItemGroup.REDSTONE);
        return this;
    }

}
