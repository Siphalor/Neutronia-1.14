package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import team.hollow.neutronia.INeutroniaInfo;

public class FrostedGlassBlock extends BaseModBlock implements INeutroniaInfo {

    public FrostedGlassBlock() {
        super(FabricBlockSettings.of(Material.GLASS).strength(3.0F, 10.0F), "frosted_glass");
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

    public boolean isWaterLogged(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

}