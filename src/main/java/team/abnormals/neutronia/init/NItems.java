package team.abnormals.neutronia.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;

import static team.abnormals.neutronia.init.NEntityTypes.*;

public class NItems {

    public static Item BAMBOO_SIGN;
    public static final Item SOCIAL_VILLAGER_MALE_EGG = Registry.register(Registry.ITEM, "neutronia:social_villager_male_egg", new SpawnEggItem(SOCIAL_VILLAGER_MALE, 5636095, 170, new Item.Settings().itemGroup(ItemGroup.MISC)));
    public static final Item SOCIAL_VILLAGER_FEMALE_EGG = Registry.register(Registry.ITEM, "neutronia:social_villager_female_egg", new SpawnEggItem(SOCIAL_VILLAGER_FEMALE, 16733525, 11141120, new Item.Settings().itemGroup(ItemGroup.MISC)));
    public static final Item SOCIAL_VILLAGER_GENDERLESS_EGG = Registry.register(Registry.ITEM, "neutronia:social_villager_genderless_egg", new SpawnEggItem(SOCIAL_VILLAGER_FEMALE, 16733525, 11141120, new Item.Settings().itemGroup(ItemGroup.MISC)));

    public static void init() {
//        BAMBOO_SIGN = Registry.register(Registry.ITEM, new Identifier("neutronia", "bamboo_sign"), new SignItem((new Item.Settings()).stackSize(16).itemGroup(ItemGroup.DECORATIONS), NBlocks.BAMBOO_SIGN, NBlocks.BAMBOO_WALL_SIGN));
    }

}
