package team.abnormals.neutronia.init;

import net.minecraft.block.Block;
import team.abnormals.neutronia.blocks.FrostedGlassBlock;
import team.abnormals.neutronia.blocks.FrostedGlassPaneBlock;

public class FrostedGlass {

    private static Block frostedGlass, frostedGlassPane;

    public static void init() {
        frostedGlass = new FrostedGlassBlock();
        frostedGlassPane = new FrostedGlassPaneBlock();
    }

}
