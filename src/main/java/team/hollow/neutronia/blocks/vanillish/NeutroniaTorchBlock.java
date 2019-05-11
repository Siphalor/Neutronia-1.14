package team.hollow.neutronia.blocks.vanillish;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;

public class NeutroniaTorchBlock extends TorchBlock {

    public NeutroniaTorchBlock() {
        super(FabricBlockSettings.copy(Blocks.TORCH).build());
    }

}
