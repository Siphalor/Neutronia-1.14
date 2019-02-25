package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.INeutroniaInfo;

public class NeutroniaStairBlock extends StairsBlock implements INeutroniaInfo {

    public NeutroniaStairBlock(BlockState blockState_1, String name) {
        super(blockState_1, Settings.copy(blockState_1.getBlock()));
        register(name + "_stairs", this);
    }

    private void register(String name, Block block) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(ItemGroup.BUILDING_BLOCKS));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}