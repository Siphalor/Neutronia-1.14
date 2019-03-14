package team.hollow.neutronia.init;

import net.minecraft.class_4174;
import net.minecraft.class_4177;
import net.minecraft.item.*;
import net.minecraft.item.block.SignItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.items.NotebookItem;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import static team.hollow.neutronia.init.NEntityTypes.SOCIAL_VILLAGER;

public class NItems {

    public static final Item BAMBOO_SIGN;
    public static final Item SOCIAL_VILLAGER_EGG;
    public static final Item BLUEBERRY;
    public static final Item GOOSEBERRIES;
    public static final Item WITHER_BERRIES;
    public static final Item GREEN_GRAPES;
    public static final Item PURPLE_GRAPES;
    public static final Item NOTEBOOK;

    static {
        BAMBOO_SIGN = Registry.register(Registry.ITEM, new Identifier(Neutronia.MOD_ID, "bamboo_sign"), new SignItem((new Item.Settings()).stackSize(16).itemGroup(ItemGroup.DECORATIONS), NBlocks.BAMBOO_SIGN, NBlocks.BAMBOO_WALL_SIGN));
        SOCIAL_VILLAGER_EGG = Registry.register(Registry.ITEM, "neutronia:social_villager_egg", new SpawnEggItem(SOCIAL_VILLAGER, 5636095, 170, new Item.Settings().itemGroup(ItemGroup.MISC)));
        BLUEBERRY = Registry.register(Registry.ITEM, "neutronia:blueberry", new class_4177(NBlocks.BLUE_BERRY_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265((new class_4174.class_4175()).method_19238(2).method_19237(0.1F).method_19242())));
        GOOSEBERRIES = Registry.register(Registry.ITEM, "neutronia:gooseberries", new class_4177(NBlocks.GOOSEBERRY_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265((new class_4174.class_4175()).method_19238(2).method_19237(0.1F).method_19242())));
        WITHER_BERRIES = Registry.register(Registry.ITEM, "neutronia:wither_berries", new class_4177(NBlocks.WITHER_BERRY_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265((new class_4174.class_4175()).method_19238(2).method_19237(0.1F).method_19242())));
        GREEN_GRAPES = Registry.register(Registry.ITEM, "neutronia:green_grapes", new class_4177(NBlocks.GREEN_GRAPE_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265((new class_4174.class_4175()).method_19238(2).method_19237(0.1F).method_19242())));
        PURPLE_GRAPES = Registry.register(Registry.ITEM, "neutronia:purple_grapes", new class_4177(NBlocks.PURPLE_GRAPE_BUSH, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PLACE, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265((new class_4174.class_4175()).method_19238(2).method_19237(0.1F).method_19242())));
        NOTEBOOK = RegistryUtils.registerItem(new NotebookItem(), "notebook");
    }

}
