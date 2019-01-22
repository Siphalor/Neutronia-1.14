package team.abnormals.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.blocks.IModBlockInfo;

public class RegistryUtils {

    public static Block register(String name, Block block, IModBlockInfo blockInfo, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, blockInfo.getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(itemGroup));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, blockInfo.getPrefix() + name, item);
        return Registry.register(Registry.BLOCK, name, block);
    }

}
