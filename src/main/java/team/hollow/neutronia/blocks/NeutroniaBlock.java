package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class NeutroniaBlock extends Block {

    public NeutroniaBlock(Material material) {
        this(FabricBlockSettings.of(material));
    }

    public NeutroniaBlock(Material material, float hardness, float resistant) {
        this(FabricBlockSettings.of(material).strength(hardness, resistant));
    }

    public NeutroniaBlock(FabricBlockSettings builder) {
        super(builder.build());
    }

}