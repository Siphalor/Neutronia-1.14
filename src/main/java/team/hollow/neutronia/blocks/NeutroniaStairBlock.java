package team.hollow.neutronia.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class NeutroniaStairBlock extends StairsBlock{

    public NeutroniaStairBlock(BlockState blockState_1) {
        super(blockState_1, Settings.copy(blockState_1.getBlock()));
    }

}