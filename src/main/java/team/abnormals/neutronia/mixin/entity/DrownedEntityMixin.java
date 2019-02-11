package team.abnormals.neutronia.mixin.entity;

import net.minecraft.entity.ai.RangedAttacker;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrownedEntity.class)
public abstract class DrownedEntityMixin extends ZombieEntity implements RangedAttacker {

    public DrownedEntityMixin(World world_1) {
        super(world_1);
    }

    @Inject(method = "method_7208", at = @At("RETURN"))
    private void method_7208(CallbackInfo info) {
        super.initGoals();
        this.goalSelector.add(1, new FleeEntityGoal<>(this, TurtleEntity.class, 8.0F, 0.6D, 0.6D));
    }

}
