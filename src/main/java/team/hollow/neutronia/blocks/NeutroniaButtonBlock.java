package team.hollow.neutronia.blocks;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class NeutroniaButtonBlock extends AbstractButtonBlock {

    boolean wooden;

    public NeutroniaButtonBlock(boolean wooden) {
        super(wooden, Settings.of(wooden ? Material.WOOD : Material.STONE));
        this.wooden = wooden;
    }

    @Override
    protected SoundEvent getClickSound(boolean pressed) {
        return this.wooden ? pressed ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF :
                pressed ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

}
