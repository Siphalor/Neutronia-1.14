package team.abnormals.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public abstract class NeutroniaPressurePlateBlock extends PressurePlateBlock implements INeutroniaBlock {

    public NeutroniaPressurePlateBlock(String name, Material material, Type sensitivity) {
        super(sensitivity, Settings.of(material));
        register(name);
    }

    public void register(String name) {
        Registry.register(Registry.BLOCK, getPrefix() + name, this);
        BlockItem item = new BlockItem(this, new Item.Settings().itemGroup(ItemGroup.REDSTONE));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}
