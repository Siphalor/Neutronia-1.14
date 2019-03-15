package team.hollow.neutronia.init;

import net.minecraft.class_4176;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.StringItem;
import net.minecraft.item.block.SignItem;
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
        BLUEBERRY = Registry.register(Registry.ITEM, "neutronia:blueberry", new StringItem(NBlocks.BLUE_BERRY_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265(class_4176.field_18636)));
        GOOSEBERRIES = Registry.register(Registry.ITEM, "neutronia:gooseberries", new StringItem(NBlocks.GOOSEBERRY_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265(class_4176.field_18636)));
        WITHER_BERRIES = Registry.register(Registry.ITEM, "neutronia:wither_berries", new StringItem(NBlocks.WITHER_BERRY_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265(class_4176.field_18636)));
        GREEN_GRAPES = Registry.register(Registry.ITEM, "neutronia:green_grapes", new StringItem(NBlocks.GREEN_GRAPE_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265(class_4176.field_18636)));
        PURPLE_GRAPES = Registry.register(Registry.ITEM, "neutronia:purple_grapes", new StringItem(NBlocks.PURPLE_GRAPE_BUSH, (new Item.Settings()).itemGroup(ItemGroup.FOOD).method_19265(class_4176.field_18636)));
        NOTEBOOK = RegistryUtils.registerItem(new NotebookItem(), "notebook");
    }

}
