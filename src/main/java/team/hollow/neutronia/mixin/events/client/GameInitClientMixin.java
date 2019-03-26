package team.hollow.neutronia.mixin.events.client;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;
//import  net.minecraft.client.util.Window;

@Mixin(MinecraftClient.class)
public class GameInitClientMixin {
	
	//this.window.setPhase("Post startup");
	//@Inject(at = @At("HEAD"), method = "init()V")
	@Inject(method = "init",
    		at = @At(value = "INVOKE_STRING",
    		target = "Lnet/minecraft/client/util/Window;setPhase(Ljava/lang/String;)V",
    		args = {"ldc=Post startup"})
    )
	private void init(CallbackInfo info) {
		System.out.println("AutoSteve mixin on Post startup MinecraftClient init.");
		EventCore.instance.onMinecraftClientPostStartupResourceHook();
		
		//.info.World c;
		//Entity p;
		//World w;
		//w.blockEntities
		
	}
}
