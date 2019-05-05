package team.hollow.neutronia.blocks;

import net.minecraft.block.FenceBlock;

public class NeutroniaFenceBlock extends FenceBlock {

    public NeutroniaFenceBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateFactory.getDefaultState().with(NORTH, Boolean.FALSE).with(EAST, Boolean.FALSE).with(SOUTH, Boolean.FALSE).with(WEST, Boolean.FALSE));
    }

}