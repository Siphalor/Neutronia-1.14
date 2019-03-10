package team.hollow.neutronia.entity.ai;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import team.hollow.neutronia.entity.EntityMummy;

public class EntityAIMummyAttack extends MeleeAttackGoal {

    private final EntityMummy mummy;
    private int raiseArmTicks;

    public EntityAIMummyAttack(EntityMummy mummyIn, double speedIn, boolean longMemoryIn) {
        super(mummyIn, speedIn, longMemoryIn);
        this.mummy = mummyIn;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void onRemove() {
        super.onRemove();
        this.mummy.setArmsRaised(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.field_6505 < 10) {
            this.mummy.setArmsRaised(true);
        } else {
            this.mummy.setArmsRaised(false);
        }
    }
}
