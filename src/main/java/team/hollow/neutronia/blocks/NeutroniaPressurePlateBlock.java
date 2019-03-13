package team.hollow.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;

public class NeutroniaPressurePlateBlock extends PressurePlateBlock {

    public NeutroniaPressurePlateBlock(Material material, Type sensitivity) {
        super(sensitivity, Settings.of(material));
    }

}
