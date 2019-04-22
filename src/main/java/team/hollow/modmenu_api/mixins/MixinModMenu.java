package team.hollow.modmenu_api.mixins;

import io.github.prospector.modmenu.ModMenu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.modmenu_api.ModMenuBadgeManager;
import team.hollow.modmenu_api.api.ModMenuBadges;

@Mixin(ModMenu.class)
public class MixinModMenu {

    @Shadow @Final public static String MOD_ID;

    @Inject(method = "onInitializeClient", at = @At("RETURN"))
    public void onInitializeClient(CallbackInfo info) {
        ModMenuBadgeManager.registerBadges(MOD_ID, ModMenuBadges.API);
    }

}
