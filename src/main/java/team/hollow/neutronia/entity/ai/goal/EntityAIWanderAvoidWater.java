package team.hollow.neutronia.entity.ai.goal;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderAvoidWater extends WanderAroundGoal
{
    protected final float probability;

    public EntityAIWanderAvoidWater(MobEntityWithAi p_i47301_1_, double p_i47301_2_)
    {
        this(p_i47301_1_, p_i47301_2_, 0.001F);
    }

    public EntityAIWanderAvoidWater(MobEntityWithAi p_i47302_1_, double p_i47302_2_, float p_i47302_4_)
    {
        super(p_i47302_1_, p_i47302_2_);
        this.probability = p_i47302_4_;
    }

    protected Vec3d getPosition()
    {
        if (this.owner.isInWater())
        {
            Vec3d vec3d = RandomPositionGenerator.getLandPos(this.owner, 15, 7);
            return vec3d == null ? super.getPosition() : vec3d;
        }
        else
        {
            return this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : super.getPosition();
        }
    }
}