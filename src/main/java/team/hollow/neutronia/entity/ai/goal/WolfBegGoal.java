package team.hollow.neutronia.entity.ai.goal;

import net.minecraft.class_4051;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import team.hollow.neutronia.entity.ArcticWolfEntity;

public class WolfBegGoal extends Goal {
    private final ArcticWolfEntity owner;
    private final World world;
    private final float chance;
    private final class_4051 field_18085;
    private PlayerEntity begFrom;
    private int timer;

    public WolfBegGoal(ArcticWolfEntity wolfEntity_1, float float_1) {
        this.owner = wolfEntity_1;
        this.world = wolfEntity_1.world;
        this.chance = float_1;
        this.field_18085 = (new class_4051()).method_18418((double) float_1).method_18417().method_18421().method_18423();
        this.setControlBits(2);
    }

    public boolean canStart() {
        this.begFrom = this.world.method_18462(this.field_18085, this.owner);
        return this.begFrom != null && this.method_6244(this.begFrom);
    }

    public boolean shouldContinue() {
        if (!this.begFrom.isValid()) {
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

    public void onRemove() {
        this.owner.setTamed(false);
        this.begFrom = null;
    }

    public void tick() {
        this.owner.getLookControl().lookAt(this.begFrom.x, this.begFrom.y + (double) this.begFrom.getStandingEyeHeight(), this.begFrom.z, 10.0F, (float) this.owner.method_5978());
        --this.timer;
    }

    private boolean method_6244(PlayerEntity playerEntity_1) {
        Hand[] var2 = Hand.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Hand hand_1 = var2[var4];
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
