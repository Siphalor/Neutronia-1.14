package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Material;

public class NeutroniaFlowerPotBlock extends FlowerPotBlock {

    public NeutroniaFlowerPotBlock(Block block_1) {
        super(block_1, FabricBlockSettings.of(Material.PART).breakInstantly().build());
    }

}
