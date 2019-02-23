package team.abnormals.neutronia.mixin.client;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.abnormals.neutronia.client.gui.ConnectionStatusRenderer;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    /**
     * Fixes a crash where the screen gets resized after GameRenderer was created but before WorldRenderer was created
     */
    @Redirect(method = "onResized(II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;onResized(II)V"))
    private void method_3169(WorldRenderer worldRenderer, int int_1, int int_2) {
        if (worldRenderer != null) {
            worldRenderer.onResized(int_1, int_2);
        }
    }

    @Inject(method = "render(FJZ)V", at = @At("RETURN"))
    private void render(float float_1, long long_1, boolean boolean_1, CallbackInfo ci) {
        ConnectionStatusRenderer.draw();
    }

}