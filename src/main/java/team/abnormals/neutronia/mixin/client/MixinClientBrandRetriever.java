package team.abnormals.neutronia.mixin.client;

import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import team.abnormals.neutronia.utils.BrandingUtil;

@Mixin(value = {ClientBrandRetriever.class}, remap = false)
public abstract class MixinClientBrandRetriever {

    @Inject(at = {@org.spongepowered.asm.mixin.injection.At("RETURN")}, method = "getClientModName", cancellable = true)
    private static void getClientModName(org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable<java.lang.String> cir) {
        BrandingUtil.brand(cir);
    }

}