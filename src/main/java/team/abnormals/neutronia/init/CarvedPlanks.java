package team.abnormals.neutronia.init;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import team.abnormals.neutronia.blocks.BlockNeutroniaBase;

public class CarvedPlanks {

    private static Block carvedOakPlanks, carvedSprucePlanks, carvedBirchPlanks, carvedJunglePlanks, carvedAcaciaPlanks, carvedDarkOakPlanks;

    public static void init() {
        carvedOakPlanks = new BlockNeutroniaBase(Material.WOOD, "carved_oak_planks");
        carvedSprucePlanks = new BlockNeutroniaBase(Material.WOOD, "carved_spruce_planks");
        carvedBirchPlanks = new BlockNeutroniaBase(Material.WOOD, "carved_birch_planks");
        carvedJunglePlanks = new BlockNeutroniaBase(Material.WOOD, "carved_jungle_planks");
        carvedAcaciaPlanks = new BlockNeutroniaBase(Material.WOOD, "carved_acacia_planks");
        carvedDarkOakPlanks = new BlockNeutroniaBase(Material.WOOD, "carved_dark_oak_planks");
    }

}
