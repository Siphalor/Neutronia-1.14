package team.abnormals.neutronia.entity.wip;

import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.world.World;
import team.abnormals.neutronia.init.NEntityTypes;

public class EntityJungleFrog extends MobEntityWithAi {

    public EntityJungleFrog(World worldIn) {
        super(NEntityTypes.JUNGLE_FROG, worldIn);
    }

    protected void initGoals() {
        super.initGoals();
    }

}