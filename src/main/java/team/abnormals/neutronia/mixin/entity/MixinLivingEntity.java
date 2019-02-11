package team.abnormals.neutronia.mixin.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.abnormals.neutronia.api.Climbable;

@Mixin(LivingEntity.class)
final class MixinLivingEntity {
    @Inject(
            method = "canClimb",
            at = @At(value = "RETURN", ordinal = 2),
            locals = LocalCapture.CAPTURE_FAILHARD,
            allow = 1,
            cancellable = true
    )
    private void canClimb(final CallbackInfoReturnable<Boolean> cir, final BlockState state, final Block block) {
        if (block instanceof Climbable) {
            final LivingEntity self = (LivingEntity) (Object) this;
            cir.setReturnValue(((Climbable) block).canClimb(self, state, self.getPos()));
        }
    }
}