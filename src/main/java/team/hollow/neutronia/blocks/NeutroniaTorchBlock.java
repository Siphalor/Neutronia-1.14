package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.TorchBlock;

public class NeutroniaTorchBlock extends TorchBlock {

    public NeutroniaTorchBlock() {
        super(FabricBlockSettings.of(Material.WOOD).build());
    }

}
