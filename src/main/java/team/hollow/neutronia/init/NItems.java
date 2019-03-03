package team.hollow.neutronia.init;

import net.minecraft.item.FoodCropItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;

import static team.hollow.neutronia.init.NEntityTypes.SOCIAL_VILLAGER;

public class NItems {

    //    private static final Item BAMBOO_SIGN;
    public static final Item SOCIAL_VILLAGER_EGG;
    public static final Item BLUEBERRY;
    public static final Item GOOSEBERRIES;
    public static final Item WITHER_BERRIES;
    public static final Item GREEN_GRAPES;
    public static final Item PURPLE_GRAPES;

    static {
//        BAMBOO_SIGN = Registry.register(Registry.ITEM, new Identifier("neutronia", "bamboo_sign"), new SignItem((new Item.Settings()).stackSize(16).itemGroup(ItemGroup.DECORATIONS), NBlocks.BAMBOO_SIGN, NBlocks.BAMBOO_WALL_SIGN));
        SOCIAL_VILLAGER_EGG = Registry.register(Registry.ITEM, "neutronia:social_villager_egg", new SpawnEggItem(SOCIAL_VILLAGER, 5636095, 170, new Item.Settings().itemGroup(ItemGroup.MISC)));
        BLUEBERRY = Registry.register(Registry.ITEM, "neutronia:blueberry", new FoodCropItem(2, 0.1F, NBlocks.BLUE_BERRY_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD)));
        GOOSEBERRIES = Registry.register(Registry.ITEM, "neutronia:gooseberries", new FoodCropItem(2, 0.1F, NBlocks.GOOSEBERRY_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD)));
        WITHER_BERRIES = Registry.register(Registry.ITEM, "neutronia:wither_berries", new FoodCropItem(2, 0.1F, NBlocks.WITHER_BERRY_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD)));
        GREEN_GRAPES = Registry.register(Registry.ITEM, "neutronia:green_grapes", new FoodCropItem(2, 0.1F, NBlocks.GREEN_GRAPE_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD)));
        PURPLE_GRAPES = Registry.register(Registry.ITEM, "neutronia:purple_grapes", new FoodCropItem(2, 0.1F, NBlocks.PURPLE_GRAPE_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD)));
    }

}
