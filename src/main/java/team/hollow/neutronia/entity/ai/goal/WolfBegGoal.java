package team.hollow.neutronia.entity.ai.goal;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.ArcticWolfEntity;

import java.util.EnumSet;

public class WolfBegGoal extends Goal {
    private final ArcticWolfEntity owner;
    private final World world;
    private final float chance;
    private final TargetPredicate field_18085;
    private PlayerEntity begFrom;
    private int timer;

    public WolfBegGoal(ArcticWolfEntity wolfEntity_1, float float_1) {
        this.owner = wolfEntity_1;
        this.world = wolfEntity_1.world;
        this.chance = float_1;
        this.field_18085 = (new TargetPredicate()).setBaseMaxDistance((double) float_1).includeInvulnerable().includeTeammates().ignoreEntityTargetRules();
        this.setControls(EnumSet.of(Goal.Control.LOOK));
    }

    public boolean canStart() {
        this.begFrom = this.world.getClosestPlayer(this.field_18085, this.owner);
        return this.begFrom != null && this.method_6244(this.begFrom);
    }

    public boolean shouldContinue() {
        if (!this.begFrom.isAlive()) {
            return false;
        } else if (this.owner.squaredDistanceTo(this.begFrom) > (double) (this.chance * this.chance)) {
            return false;
        } else {
            return this.timer > 0 && this.method_6244(this.begFrom);
        }
    }

    public void start() {
        this.owner.setBegging(true);
        this.timer = 40 + this.owner.getRand().nextInt(40);
    }

    public void stop() {
        this.owner.setTamed(false);
        this.begFrom = null;
    }

    public void tick() {
        this.owner.getLookControl().lookAt(this.begFrom.x, this.begFrom.y + (double) this.begFrom.getStandingEyeHeight(), this.begFrom.z, 10.0F, (float) this.owner.getLookPitchSpeed());
        --this.timer;
    }

    private boolean method_6244(PlayerEntity playerEntity_1) {
        Hand[] var2 = Hand.values();

        for (Hand hand_1 : var2) {
            ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
            if (this.owner.isTamed() && itemStack_1.getItem() == Items.BONE) {
                return true;
            }

            if (this.owner.isBreedingItem(itemStack_1)) {
                return true;
            }
        }

        return false;
    }
}
