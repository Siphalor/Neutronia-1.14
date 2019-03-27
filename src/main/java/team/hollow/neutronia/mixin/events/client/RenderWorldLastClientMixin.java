package team.hollow.neutronia.mixin.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;

@Mixin(GameRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class RenderWorldLastClientMixin {
    @Inject(method = "renderCenter",
            at = @At(value = "INVOKE_STRING",
                    target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V",
                    args = {"ldc=hand"})
    )
    private void onrenderCenter_getProfiler_swap_hand(float float_1, long long_1, final CallbackInfo ci) {
        EventCore.instance.onRenderWorldLast(float_1);
    }
}
