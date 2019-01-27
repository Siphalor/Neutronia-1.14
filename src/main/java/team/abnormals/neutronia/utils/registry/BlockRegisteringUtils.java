package team.abnormals.neutronia.utils.registry;

import net.minecraft.block.Block;
import team.abnormals.neutronia.blocks.*;

public class BlockRegisteringUtils {

    public static void addSlabAndStair(String name, Block block, boolean doit) {
        addSlabAndStair(name, block, true, true, doit);
    }

    public static void addSlabAndStair(String name, Block block, boolean slab, boolean stairs, boolean doit) {
        if (!doit)
            return;

        String stairsName = name + "_stairs";

        if (stairs)
            new BlockNeutroniaStair(block.getDefaultState(), stairsName);
        if (slab) {
            new BlockNeutroniaSlab(name);
        }
    }

    public static void addFenceAndGate(String name, Block block, boolean doIt) {
        addFenceAndGate(name, block, true, true, doIt);
    }

    public static void addFenceAndGate(String name, Block block, boolean fence, boolean fenceGate, boolean doIt) {
        if (!doIt)
            return;

        String wallName;

        if (fence) {
            wallName = name + "_fence";
            new BlockNeutroniaFence(wallName, block.getDefaultState());
        }

        if (fenceGate) {
            wallName = name + "_fence_gate";
            new BlockNeutroniaFenceGate(wallName);
        }
    }

    public static void addWalls(String name, Block block, boolean doit) {
        if (!doit)
            return;

        String wallName = name + "_wall";
        new BlockNeutroniaWall(wallName, block.getDefaultState());
    }

}
