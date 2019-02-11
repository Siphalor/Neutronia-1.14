package team.abnormals.neutronia.blocks;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Material;

public class FrostedGlassPaneBlock extends NeutroniaPaneBlock {

    public FrostedGlassPaneBlock() {
        super(Settings.of(Material.GLASS).strength(3.0F, 10.0F), "frosted_glass_pane");
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

}