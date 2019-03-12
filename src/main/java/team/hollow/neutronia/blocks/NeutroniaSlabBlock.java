package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;

public class NeutroniaSlabBlock extends SlabBlock{

    public NeutroniaSlabBlock() {
        super(FabricBlockSettings.of(Material.WOOD).build());
    }

}