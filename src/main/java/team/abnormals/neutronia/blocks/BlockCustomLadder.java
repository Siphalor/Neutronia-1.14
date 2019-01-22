package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class BlockCustomLadder extends LadderBlock implements INeutroniaBlock {

    public BlockCustomLadder(String variant) {
        super(FabricBlockSettings.of(Material.PART).hardness(0.4F).sounds(BlockSoundGroup.LADDER).build());
        setDefaultState(this.getDefaultState().with(field_11253, Direction.NORTH).with(field_11257, false));
        register(variant + "_ladder", this, ItemGroup.DECORATIONS);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}