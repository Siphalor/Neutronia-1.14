package team.hollow.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import team.hollow.neutronia.blocks.*;
import team.hollow.neutronia.blocks.NeutroniaCornerBlock;
import team.hollow.neutronia.blocks.NeutroniaPostBlock;
import team.hollow.neutronia.blocks.NeutroniaSidingBlock;

public class BlockRegistryBuilder {

    public static final BlockRegistryBuilder INSTANCE = new BlockRegistryBuilder();
    private static Block baseBlock;
    private static String name;

    public static BlockRegistryBuilder getInstance(String nameIn, Block blockIn) {
        name = nameIn;
        baseBlock = blockIn;
        return INSTANCE;
    }

    public BlockRegistryBuilder slab() {
        RegistryUtils.register(new NeutroniaSlabBlock(), name + "_slab", ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public BlockRegistryBuilder stair() {
        RegistryUtils.register(new NeutroniaStairBlock(baseBlock.getDefaultState()), name + "_stairs", ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public BlockRegistryBuilder fence() {
        RegistryUtils.register(new NeutroniaFenceBlock(baseBlock.getDefaultState()), name + "_fence");
        return this;
    }

    public BlockRegistryBuilder fenceGate() {
        RegistryUtils.register(new NeutroniaFenceGateBlock(), name + "_fence_gate", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder wall() {
        RegistryUtils.register(new NeutroniaWallBlock(baseBlock.getDefaultState()), name + "_wall", ItemGroup.DECORATIONS);
        return this;
    }

    public BlockRegistryBuilder button(boolean wooden) {
        RegistryUtils.register(new NeutroniaButtonBlock(wooden), name + "_button", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder pressurePlate(PressurePlateBlock.Type type) {
        RegistryUtils.register(new NeutroniaPressurePlateBlock(baseBlock.getMaterial(baseBlock.getDefaultState()), type), name + "_pressure_plate", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder corner() {
        RegistryUtils.register(new NeutroniaCornerBlock(baseBlock.getDefaultState(),Block.Settings.copy(baseBlock)), name + "_corner");
        return this;
    }

    public BlockRegistryBuilder post() {
        RegistryUtils.register(new NeutroniaPostBlock(Block.Settings.copy(baseBlock)), name + "_post");
        return this;
    }

    public BlockRegistryBuilder siding() {
        RegistryUtils.register(new NeutroniaSidingBlock(Block.Settings.copy(baseBlock)), name + "_siding");
        return this;
    }

}
