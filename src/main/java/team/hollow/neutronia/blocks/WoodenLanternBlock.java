package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import team.hollow.neutronia.enums.VanillaAndModdedMinusBambooWoodTypes;
import team.hollow.neutronia.enums.VanillaWoodTypes;

public class WoodenLanternBlock extends BaseModBlock {

    public WoodenLanternBlock(VanillaAndModdedMinusBambooWoodTypes woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10), String.format("%s_lantern", woodType.asString()));
    }

    public WoodenLanternBlock(VanillaWoodTypes woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10), String.format("%s_lantern", woodType.asString()));
    }

}