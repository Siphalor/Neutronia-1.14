package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;
import team.abnormals.neutronia.enums.VanillaWoodTypes3;
import team.abnormals.neutronia.enums.VanillaWoodTypes4;

public class WoodenLanternBlock extends BaseModBlock implements INeutroniaBlock {

    public WoodenLanternBlock(VanillaWoodTypes4 woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10).build(), String.format("%s_lantern", woodType.asString()));
    }

    public WoodenLanternBlock(VanillaWoodTypes3 woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10).build(), String.format("%s_lantern", woodType.asString()));
    }

}