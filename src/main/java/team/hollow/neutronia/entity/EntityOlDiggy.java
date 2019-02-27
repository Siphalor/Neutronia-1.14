package team.hollow.neutronia.entity;

import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.world.World;
import team.hollow.neutronia.init.NEntityTypes;

public class EntityOlDiggy extends MobEntityWithAi {

    public EntityOlDiggy(World worldIn) {
        super(NEntityTypes.OL_DIGGY, worldIn);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AvoidSunlightGoal(this));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getAttributeContainer().register(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getAttributeContainer().register(EntityAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

}