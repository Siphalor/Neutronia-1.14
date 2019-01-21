package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;
import team.abnormals.neutronia.enums.WoodTypes;
import team.abnormals.neutronia.enums.WoodTypesVanilla;

public class BlockWoodenLantern extends BlockModBase implements INeutroniaBlock {

    public BlockWoodenLantern(WoodTypes woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10).build(), String.format("%s_lantern", woodType.asString()));
    }

    public BlockWoodenLantern(WoodTypesVanilla woodType) {
        super(FabricBlockSettings.of(Material.WOOD).ticksRandomly().lightLevel(10).build(), String.format("%s_lantern", woodType.asString()));
    }

}