package team.hollow.neutronia.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;

import static team.hollow.neutronia.init.NEntityTypes.SOCIAL_VILLAGER;

public class NItems {

//    private static final Item BAMBOO_SIGN;
    private static final Item SOCIAL_VILLAGER_EGG;

    static  {
//        BAMBOO_SIGN = Registry.register(Registry.ITEM, new Identifier("neutronia", "bamboo_sign"), new SignItem((new Item.Settings()).stackSize(16).itemGroup(ItemGroup.DECORATIONS), NBlocks.BAMBOO_SIGN, NBlocks.BAMBOO_WALL_SIGN));
        SOCIAL_VILLAGER_EGG = Registry.register(Registry.ITEM, "neutronia:social_villager_egg", new SpawnEggItem(SOCIAL_VILLAGER, 5636095, 170, new Item.Settings().itemGroup(ItemGroup.MISC)));;
    }

}
