package team.abnormals.neutronia.blocks;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;

public class NeutroniaButtonBlock extends AbstractButtonBlock implements INeutroniaBlock {

    private final String bareName;

    boolean wooden;

    public NeutroniaButtonBlock(String name, boolean wooden) {
        super(wooden, Settings.of(wooden ? Material.WOOD : Material.STONE));

        this.wooden = wooden;
        bareName = name;

        register(bareName);
    }

    public void register(String name) {
        Registry.register(Registry.BLOCK, getPrefix() + name, this);
        BlockItem item = new BlockItem(this, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

    @Override
    protected SoundEvent getClickSound(boolean wooden) {
        return wooden ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }

}
