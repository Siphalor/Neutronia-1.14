package team.hollow.neutronia.init;

import net.minecraft.item.FoodItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.util.registry.Registry;

public class NItems {

//    public static final Item BAMBOO_SIGN;
    public static final Item BLUEBERRY;
    public static final Item GOOSEBERRIES;
    public static final Item WITHER_BERRIES;
    public static final Item GREEN_GRAPES;
    public static final Item PURPLE_GRAPES;

    static {
//        BAMBOO_SIGN = Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, "bamboo_sign"), new SignItem((new Item.Settings()).stackSize(16).itemGroup(ItemGroup.DECORATIONS), NBlocks.BAMBOO_SIGN, NBlocks.BAMBOO_WALL_SIGN));
        BLUEBERRY = Registry.register(Registry.ITEM, "neutronia:blueberry", new AliasedBlockItem(NBlocks.BLUE_BERRY_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).food(FoodItemSettings.SWEET_BERRIES)));
        GOOSEBERRIES = Registry.register(Registry.ITEM, "neutronia:gooseberries", new AliasedBlockItem(NBlocks.GOOSEBERRY_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).food(FoodItemSettings.SWEET_BERRIES)));
        WITHER_BERRIES = Registry.register(Registry.ITEM, "neutronia:wither_berries", new AliasedBlockItem(NBlocks.WITHER_BERRY_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).food(FoodItemSettings.SWEET_BERRIES)));
        GREEN_GRAPES = Registry.register(Registry.ITEM, "neutronia:green_grapes", new AliasedBlockItem(NBlocks.GREEN_GRAPE_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).food(FoodItemSettings.SWEET_BERRIES)));
        PURPLE_GRAPES = Registry.register(Registry.ITEM, "neutronia:purple_grapes", new AliasedBlockItem(NBlocks.PURPLE_GRAPE_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).food(FoodItemSettings.SWEET_BERRIES)));
    }

}
