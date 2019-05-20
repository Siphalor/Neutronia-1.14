package team.hollow.neutronia.blocks.melons;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;

public class BaseModBlock extends Block {

    public BaseModBlock(FabricBlockSettings settings) {
        super(settings.build());
    }

    public BaseModBlock(Material material) {
        super(FabricBlockSettings.of(material).build());
    }

    public BaseModBlock(Settings settings) {
        super(settings);
    }

    public boolean method_9526(BlockState blockState_1) {
        return true;
    }

    public boolean isFullBoundsCubeForCulling(BlockState blockState_1) {
        return true;
    }

    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

}