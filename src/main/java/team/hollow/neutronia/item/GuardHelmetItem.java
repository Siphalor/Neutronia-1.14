package team.hollow.neutronia.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class GuardHelmetItem extends ArmorItem {

    public GuardHelmetItem() {
        super(ArmorMaterials.CHAIN, EquipmentSlot.HEAD, (new Item.Settings()).itemGroup(ItemGroup.COMBAT));
    }

}
