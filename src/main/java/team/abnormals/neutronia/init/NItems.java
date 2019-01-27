package team.abnormals.neutronia.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.abnormals.neutronia.items.SignItem;

public class NItems {

    public static Item BAMBOO_SIGN;

    public static void init()  {
        BAMBOO_SIGN = Registry.register(Registry.ITEM, new Identifier("neutronia", "bamboo_sign"), new SignItem((new Item.Settings()).stackSize(16).itemGroup(ItemGroup.DECORATIONS), NBlocks.BAMBOO_SIGN, NBlocks.BAMBOO_WALL_SIGN));
    }

}
