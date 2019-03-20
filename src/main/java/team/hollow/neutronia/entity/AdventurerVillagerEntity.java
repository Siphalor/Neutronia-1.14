package team.hollow.neutronia.entity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AbsoluteHand;
import net.minecraft.world.World;

public class AdventurerVillagerEntity extends LivingEntity {

    public AdventurerVillagerEntity(World worldIn) {
        super(FabricEntityTypeBuilder.<AdventurerVillagerEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AdventurerVillagerEntity(var2)).build(), worldIn);
    }

    @Override
    public Iterable<ItemStack> getItemsArmor() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot var1) {
        return null;
    }

    @Override
    public void setEquippedStack(EquipmentSlot var1, ItemStack var2) {

    }

    @Override
    public AbsoluteHand getMainHand() {
        return null;
    }

}