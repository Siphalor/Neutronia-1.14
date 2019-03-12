package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.PlantBlock;

public class NeutroniaBushBlock extends PlantBlock{

    public NeutroniaBushBlock() {
        super(FabricBlockSettings.of(Material.PLANT).build());
    }

}
