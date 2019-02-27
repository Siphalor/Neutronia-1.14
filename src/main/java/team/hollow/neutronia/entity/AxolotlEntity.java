package team.hollow.neutronia.entity;

import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.world.World;
import team.hollow.neutronia.init.NEntityTypes;

public class AxolotlEntity extends MobEntityWithAi {

    public AxolotlEntity(World worldIn) {
        super(NEntityTypes.AXOLOTL, worldIn);
    }

}
