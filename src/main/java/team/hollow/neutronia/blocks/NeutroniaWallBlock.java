package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;

public class NeutroniaWallBlock extends WallBlock {

    public NeutroniaWallBlock(BlockState state) {
        super(FabricBlockSettings.of(state.getMaterial()).build());
    }

}
