package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import team.hollow.neutronia.INeutroniaInfo;
import team.hollow.neutronia.blocks.entity.StoneChestBlockEntity;
import team.hollow.neutronia.enums.CustomChestTypes;

public class CustomChestBlock extends ChestBlock implements INeutroniaInfo {

    private String name;

    public CustomChestBlock(String name) {
        super(FabricBlockSettings.of(Material.STONE).hardness(3.0f).resistance(30.0f).breakByTool(ItemTags.STONE_BRICKS, 1).build());
        this.name = name;
        register(name, this);
    }

    private void register(String name, Block block) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(ItemGroup.DECORATIONS));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new StoneChestBlockEntity(CustomChestTypes.getFromName(name));
    }
}