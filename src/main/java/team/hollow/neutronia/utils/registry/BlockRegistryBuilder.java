package team.hollow.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.blocks.*;

public class BlockRegistryBuilder {

    public static final BlockRegistryBuilder INSTANCE = new BlockRegistryBuilder();
    private static Block baseBlock;
    private static String name;
    private static String modId;

    public static BlockRegistryBuilder getInstance(String nameIn, Block blockIn) {
        modId = Neutronia.MOD_ID;
        name = nameIn;
        baseBlock = blockIn;
        return INSTANCE;
    }

    public static BlockRegistryBuilder getInstance(String modIdIn, String nameIn, Block blockIn) {
        modId = modIdIn;
        name = nameIn;
        baseBlock = blockIn;
        return INSTANCE;
    }

    public BlockRegistryBuilder slab() {
        Block slab = new NeutroniaSlabBlock();
        RegistryUtils.register(modId, slab, name + "_slab", ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public BlockRegistryBuilder stair() {
        Block stair = new NeutroniaStairBlock(baseBlock.getDefaultState());
        RegistryUtils.register(modId, stair, name + "_stairs", ItemGroup.BUILDING_BLOCKS);
        return this;
    }

    public BlockRegistryBuilder fence() {
        Block fence = new NeutroniaFenceBlock(baseBlock.getDefaultState());
        RegistryUtils.register(modId, fence, name + "_fence");
        return this;
    }

    public BlockRegistryBuilder fenceGate() {
        RegistryUtils.register(modId, new NeutroniaFenceGateBlock(), name + "_fence_gate", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder wall() {
        Block wall = new NeutroniaWallBlock(baseBlock.getDefaultState());
        RegistryUtils.register(modId, wall, name + "_wall", ItemGroup.DECORATIONS);
        return this;
    }

    public BlockRegistryBuilder button(boolean wooden) {
        Block button = new NeutroniaButtonBlock(wooden);
        RegistryUtils.register(modId, button, name + "_button", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder pressurePlate(PressurePlateBlock.Type type) {
        RegistryUtils.register(modId, new NeutroniaPressurePlateBlock(baseBlock.getMaterial(baseBlock.getDefaultState()), type), name + "_pressure_plate", ItemGroup.REDSTONE);
        return this;
    }

    public BlockRegistryBuilder corner() {
        Block corner = new NeutroniaCornerBlock(baseBlock.getDefaultState(), Block.Settings.copy(baseBlock));
        RegistryUtils.register(modId, corner, name + "_corner");
        return this;
    }

    public BlockRegistryBuilder post() {
        Block post = new NeutroniaPostBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(modId, post, name + "_post");
        return this;
    }

    public BlockRegistryBuilder siding() {
        Block siding = new NeutroniaSidingBlock(Block.Settings.copy(baseBlock));
        RegistryUtils.register(modId, siding, name + "_siding");
        return this;
    }

}
