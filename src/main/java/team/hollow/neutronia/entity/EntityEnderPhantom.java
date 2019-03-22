package team.hollow.neutronia.entity;

import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;
import team.hollow.neutronia.init.NEntityTypes;

public class EntityEnderPhantom extends PhantomEntity {

    public EntityEnderPhantom(World worldIn) {
        super(NEntityTypes.ENDER_PHANTOM, worldIn);
    }


}
