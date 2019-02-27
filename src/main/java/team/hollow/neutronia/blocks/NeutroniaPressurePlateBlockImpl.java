package team.hollow.neutronia.blocks;

import net.minecraft.block.Material;

public class NeutroniaPressurePlateBlockImpl extends NeutroniaPressurePlateBlock {

    public NeutroniaPressurePlateBlockImpl(String name, Material material, Type sensitivity) {
        super(name + "_pressure_plate", material, sensitivity);
    }

}
