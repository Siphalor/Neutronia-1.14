package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import team.hollow.neutronia.enums.WoodType;

public class WoodenLanternBlock extends BaseModBlock {

    public WoodenLanternBlock(WoodType woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10), String.format("%s_lantern", woodType.asString()));
    }

}