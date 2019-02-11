package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class BarrelBaseBlock extends BarrelBlock implements INeutroniaBlock {

    public BarrelBaseBlock(String name) {
        super(FabricBlockSettings.of(Material.WOOD).hardness(2.5F).sounds(BlockSoundGroup.WOOD).build());
        register(name + "_barrel", this, ItemGroup.BUILDING_BLOCKS);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}
