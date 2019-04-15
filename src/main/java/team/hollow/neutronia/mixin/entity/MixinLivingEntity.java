/*
package team.hollow.neutronia.mixin.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.hollow.neutronia.init.NTags;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Inject(
            method = {"Lnet/minecraft/entity/LivingEntity;isClimbing()Z"},
            at = {@At(
                    value = "RETURN",
                    ordinal = 2
            )},
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    public void canClimb(CallbackInfoReturnable<Boolean> cir, BlockState state, Block block) {
        if (block.matches(NTags.CLIMBABLE)) {
            cir.setReturnValue(true);
        }
    }

}*/
