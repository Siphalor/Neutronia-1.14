package team.abnormals.neutronia.utils.registry;

import net.minecraft.block.Block;
import team.abnormals.neutronia.blocks.*;

public class BlockRegisteringUtils {

    public static void addSlabAndStair(String name, Block block) {
        addSlabAndStair(name, block, true, true);
    }

    public static void addSlabAndStair(String name, Block block, boolean slab, boolean stairs) {
        if (stairs) new NeutroniaStairBlock(block.getDefaultState(), name);
        if (slab) new NeutroniaSlabBlock(name);
    }

    public static void addFenceAndGate(String name, Block block) {
        addFenceAndGate(name, block, true, true);
    }

    public static void addFenceAndGate(String name, Block block, boolean fence, boolean fenceGate) {
        String wallName;

        if (fence) {
            wallName = name + "_fence";
            new NeutroniaFenceBlock(wallName, block.getDefaultState());
        }

        if (fenceGate) {
            wallName = name + "_fence_gate";
            new NeutroniaFenceGateBlock(wallName);
        }
    }

    public static void addWalls(String name, Block block) {
        String wallName = name + "_wall";
        new NeutroniaWallBlock(wallName, block.getDefaultState());
    }

}
