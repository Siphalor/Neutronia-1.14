package team.hollow.neutronia.entity;

import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import team.hollow.neutronia.init.NEntityTypes;

public class EntityBloodPhantom extends PhantomEntity {

    public EntityBloodPhantom(World worldIn) {
        super(NEntityTypes.BLOOD_PHANTOM, worldIn);
    }

}