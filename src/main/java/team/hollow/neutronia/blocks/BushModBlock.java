package team.hollow.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.block.PlantBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.INeutroniaInfo;

public class BushModBlock extends PlantBlock implements INeutroniaInfo {

    public BushModBlock(String name, Material material) {
        super(Settings.of(material));
        register(name);
    }

    public void register(String name) {
        Registry.register(Registry.BLOCK, getPrefix() + name, this);
        BlockItem item = new BlockItem(this, new Item.Settings().itemGroup(ItemGroup.DECORATIONS));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}