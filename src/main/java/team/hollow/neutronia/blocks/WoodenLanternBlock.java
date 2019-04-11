package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.enums.WoodType;

public class WoodenLanternBlock extends BaseModBlock {

    public WoodenLanternBlock(WoodType woodType) {
        super(FabricBlockSettings.of(Material.WOOD).breakByHand(true).drops(new Identifier(Neutronia.MOD_ID, String.format("%s_paper_lantern", woodType.asString()))).ticksRandomly().lightLevel(10), String.format("%s_paper_lantern", woodType.asString()));
    }

}