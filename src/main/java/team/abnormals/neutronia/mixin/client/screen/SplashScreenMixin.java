package team.abnormals.neutronia.mixin.client.screen;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.SplashScreen;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SplashScreen.class, remap = false)
public abstract class SplashScreenMixin extends Screen {

    @Inject(method = "draw(IIF)V", at = @At("RETURN"))
    private void draw(int int_1, int int_2, float float_1, CallbackInfo info) {

    }

    @Inject(method = "method_18103(IIIIFF)V", at = @At("RETURN"))
    private void method_18103(int int_1, int int_2, int int_3, int int_4, float float_1, float float_2, CallbackInfo info) {
        int int_5 = MathHelper.ceil((float) (int_3 - int_1 - 2) * float_1);
        drawRect(int_1 - 1, int_2 - 1, int_3 + 1, int_4 + 1, 0xFF00FF);
        drawRect(int_1, int_2, int_3, int_4, 0xFF00FF);
        drawRect(int_1 + 1, int_2 + 1, int_1 + int_5, int_4 - 1, 0xFF00FF);
    }

}