package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;

public class NeutroniaFenceBlock extends FenceBlock {

    public NeutroniaFenceBlock(Block state) {
        super(FabricBlockSettings.of(state.getStateFactory().getDefaultState().getMaterial()).hardness(2.0F).resistance(5.0F).sounds(state.getStateFactory().getDefaultState().getSoundGroup()).build());
        setDefaultState(this.stateFactory.getDefaultState().with(NORTH, Boolean.FALSE).with(EAST, Boolean.FALSE).with(SOUTH, Boolean.FALSE).with(WEST, Boolean.FALSE));
    }

}