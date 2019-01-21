package team.abnormals.neutronia.init;

import net.minecraft.block.Block;
import team.abnormals.neutronia.blocks.BlockFrostedGlass;
import team.abnormals.neutronia.blocks.BlockFrostedGlassPane;

public class FrostedGlass {

    private static Block frostedGlass, frostedGlassPane;

    public static void init() {
        frostedGlass = new BlockFrostedGlass();
        frostedGlassPane = new BlockFrostedGlassPane();
    }

}
