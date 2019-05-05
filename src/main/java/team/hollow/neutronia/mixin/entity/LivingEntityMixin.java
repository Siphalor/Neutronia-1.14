package team.hollow.neutronia.mixin.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.hollow.neutronia.api.Climbable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(
            method = "isClimbing",
            at = {@At(
                    value = "RETURN",
                    ordinal = 2
            )},
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    public void canClimb(CallbackInfoReturnable<Boolean> cir, BlockState state, Block block) {
        if (block instanceof Climbable) {
            cir.setReturnValue(((Climbable) block).canClimb((LivingEntity)(Object) this, state, new BlockPos(this)));
        }
    }

}
