package team.hollow.neutronia.mixin.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;


@Mixin(ClientWorld.class)
@Environment(EnvType.CLIENT)
public class WorldLoadClientMixin {
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onConstructor(CallbackInfo ci) {
        ClientWorld _this = (ClientWorld) (Object) this;
        EventCore.instance.onClientWorldLoad(_this);
    }
}
