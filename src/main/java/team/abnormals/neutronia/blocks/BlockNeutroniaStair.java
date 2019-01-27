package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public class BlockNeutroniaStair extends StairsBlock implements INeutroniaBlock {

    public BlockNeutroniaStair(BlockState blockState_1, String name) {
        super(blockState_1, Settings.copy(blockState_1.getBlock()));
        register(name, this);
    }

    private void register(String name, Block block) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}