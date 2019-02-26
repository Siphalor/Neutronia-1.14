package team.abnormals.neutronia.utils.registry;

import net.minecraft.block.Block;

public class BlockRegisteringUtils {

    public static void addSlabAndStair(String name, Block block) {
        BlockRegistryBuilder.getInstance(name, block).stair().slab();
    }

    public static void addWalls(String name, Block block) {
        BlockRegistryBuilder.getInstance(name, block).wall();
    }

}
