package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import team.hollow.neutronia.INeutroniaInfo;
import team.hollow.neutronia.enums.VanillaWoodTypes;
import team.hollow.neutronia.enums.VanillaAndModdedMinusBambooWoodTypes;

public class WoodenLanternBlock extends BaseModBlock implements INeutroniaInfo {

    public WoodenLanternBlock(VanillaAndModdedMinusBambooWoodTypes woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10).build(), String.format("%s_lantern", woodType.asString()));
    }

    public WoodenLanternBlock(VanillaWoodTypes woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10).build(), String.format("%s_lantern", woodType.asString()));
    }

}