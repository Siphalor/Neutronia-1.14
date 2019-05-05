package team.hollow.neutronia.blocks;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class NeutroniaButtonBlock extends AbstractButtonBlock {

    boolean wooden;

    public NeutroniaButtonBlock(boolean wooden, Settings settings) {
        super(wooden, settings);
        this.wooden = wooden;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return this.wooden ? pressed ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF :
                pressed ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

}
