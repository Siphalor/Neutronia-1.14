package team.abnormals.neutronia.entity.wip;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import team.hdt.neutronia.entity.ai.EntityAIAvoidLight;

public class EntityOlDiggy extends EntityMob {

    public EntityOlDiggy(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIAvoidLight(this, 20));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeContainer().get(EntityAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getAttributeContainer().get(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getAttributeContainer().get(EntityAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

}