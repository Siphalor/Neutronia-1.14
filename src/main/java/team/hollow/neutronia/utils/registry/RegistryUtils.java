package team.hollow.neutronia.utils.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.item.block.ScaffoldingItem;
import net.minecraft.item.block.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;

public class RegistryUtils {

    public static Block register(Block block, Identifier name) {
        return register(block, name, ItemGroup.DECORATIONS);
    }

    public static Block register(Block block, String name) {
        return register(block, new Identifier(Neutronia.MOD_ID, name), ItemGroup.DECORATIONS);
    }

    public static Block register(Block block, Identifier name, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, name, block);
        Registry.register(Registry.ITEM, name, new BlockItem(block, new Item.Settings().itemGroup(itemGroup)));
        return block;
    }

    public static Block register(Block block, String name, ItemGroup itemGroup) {
        register(block, new Identifier(Neutronia.MOD_ID, name), itemGroup);
        return block;
    }

    public static Block registerScaffolding(Block block, String name) {
        Registry.register(Registry.BLOCK, name, block);
        Registry.register(Registry.ITEM, name, new ScaffoldingItem(block, new Item.Settings().itemGroup(ItemGroup.DECORATIONS)));
        return block;
    }

    public static Block register(Block block, Block wallBlock, String name) {
        Registry.register(Registry.BLOCK, new Identifier(Neutronia.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, name), new WallStandingBlockItem(block, wallBlock, new Item.Settings().itemGroup(ItemGroup.DECORATIONS)));
        return block;
    }

    public static Block registerNoBI(Block block, String name) {
        Registry.register(Registry.BLOCK, new Identifier(Neutronia.MOD_ID, name), block);
        return block;
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, name), item);
        return item;
    }

    public static Item registerItem(Item item, Identifier name) {
        Registry.register(Registry.ITEM, name, item);
        return item;
    }

}