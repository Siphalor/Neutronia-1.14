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

    public static void register(Identifier name, Block block, ItemGroup itemGroup) {
        Registry.register(Registry.BLOCK, name, block);
        Registry.register(Registry.ITEM, name, new BlockItem(block, new Item.Settings().itemGroup(itemGroup)));
    }

    public static Block registerTest(Block block, Identifier name, ItemGroup itemGroup) {
        register(name, block, itemGroup);
        return block;
    }

    public static Block registerTest(String name, Block block, ItemGroup itemGroup) {
    public static Block registerTest(Block block, String name) {
        register(new Identifier(Neutronia.MOD_ID, name), block, ItemGroup.DECORATIONS);
        return block;
    }

    public static Block registerTest(Block block, String name, ItemGroup itemGroup) {
        register(new Identifier(Neutronia.MOD_ID, name), block, itemGroup);
        return block;
    }

    public static Block registerScaffolding(Block block, String name) {
        Registry.register(Registry.BLOCK, name, block);
        Registry.register(Registry.ITEM, name, new ScaffoldingItem(block, new Item.Settings().itemGroup(ItemGroup.DECORATIONS)));
        return block;
    }

    public static Block registerTest(Block block, Block wallBlock, String name) {
        registerWallStandingBlockNeutroniaWithBI(name, block, wallBlock, ItemGroup.DECORATIONS);
        return block;
    }

    public static Block registerTestNoBI(Block block, String name) {
        registerNeutroniaWithoutBI(name, block);
        return block;
    }

    private static void registerNeutroniaWithoutBI(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(Neutronia.MOD_ID, name), block);
    }

    private static void registerWallStandingBlockNeutroniaWithBI(String name, Block block, Block wallBlock, ItemGroup tab) {
        Registry.register(Registry.BLOCK, new Identifier(Neutronia.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, name), new WallStandingBlockItem(block, wallBlock, new Item.Settings().itemGroup(tab)));
    }

    public static Item registerItem(Item item, String name) {
        Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, name), item);
        return item;
    }

}