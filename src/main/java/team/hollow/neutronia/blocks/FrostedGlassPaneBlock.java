package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Material;

public class FrostedGlassPaneBlock extends NeutroniaPaneBlock {

    public FrostedGlassPaneBlock() {
        super(FabricBlockSettings.of(Material.GLASS).strength(3.0F, 10.0F), "frosted_glass_pane");
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

}