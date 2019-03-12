package team.hollow.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;

public abstract class NeutroniaPressurePlateBlock extends PressurePlateBlock {

    public NeutroniaPressurePlateBlock(Material material, Type sensitivity) {
        super(sensitivity, Settings.of(material));
    }

}
