package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.INeutroniaInfo;

public class NeutroniaWallBlock extends WallBlock implements INeutroniaInfo {

    public NeutroniaWallBlock(String name, BlockState state) {
        super(FabricBlockSettings.of(state.getMaterial()).build());
        register(name, this, ItemGroup.BUILDING_BLOCKS);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}
