package team.hollow.neutronia.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class GuardHelmetItem extends ArmorItem {

    public GuardHelmetItem() {
        super(ArmorMaterials.CHAIN, EquipmentSlot.HEAD, (new Item.Settings()).itemGroup(ItemGroup.COMBAT));
    }

}
