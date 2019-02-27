package team.hollow.neutronia.entity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class AlbadonEntity extends MobEntity {

    public AlbadonEntity(World worldIn) {
        super(FabricEntityTypeBuilder.<AlbadonEntity>create(EntityCategory.CREATURE, (var1, var2) -> new AlbadonEntity(var2)).build(), worldIn);
    }

}
