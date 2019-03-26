package team.hollow.neutronia.mixin.events.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public class AutoSteveMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		System.out.println("AutoSteve mixin on MinecraftClient initialization!");
		//.info.World c;
		//Entity p;
		//World w;
		//w.blockEntities
		
	}
}
