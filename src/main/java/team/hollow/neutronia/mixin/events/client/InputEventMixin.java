package team.hollow.neutronia.mixin.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;


@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public abstract class InputEventMixin {
	@Inject(at = @At("HEAD"), method = "handleInputEvents")
	private void onInputEvent(CallbackInfo info)
	{
		EventCore.instance.onInputEvent();
	}
}