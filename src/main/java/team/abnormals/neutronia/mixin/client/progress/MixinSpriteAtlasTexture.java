package team.abnormals.neutronia.mixin.client.progress;

import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.abnormals.neutronia.LoadingProgressImpl;

import java.util.Iterator;

@Mixin(SpriteAtlasTexture.class)
public abstract class MixinSpriteAtlasTexture {

    @Inject(method = "method_18159(Lnet/minecraft/client/texture/SpriteAtlasTexture$class_4007;)V", at = @At("HEAD"))
    private void startReload(SpriteAtlasTexture.class_4007 spriteAtlasTexture$class_4007_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName("Building sprite atlas");
    }

    @Inject(method = "method_18159(Lnet/minecraft/client/texture/SpriteAtlasTexture$class_4007;)V", at = @At("RETURN"))
    private void endReload(SpriteAtlasTexture.class_4007 spriteAtlasTexture$class_4007_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

    @Inject(method = "method_18159(Lnet/minecraft/client/texture/SpriteAtlasTexture$class_4007;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void startAddSprite(SpriteAtlasTexture.class_4007 spriteAtlasTexture$class_4007_1, CallbackInfo ci, Iterator var2, Sprite sprite_1) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName(String.format("Adding sprite '%s'", sprite_1.getId()));
    }

    @Inject(method = "method_18159(Lnet/minecraft/client/texture/SpriteAtlasTexture$class_4007;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/Sprite;isAnimated()Z", shift = At.Shift.BEFORE))
    private void endAddSprite(SpriteAtlasTexture.class_4007 spriteAtlasTexture$class_4007_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;)V", at = @At("HEAD"))
    private void startReload(ResourceManager resourceManager_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName("Building sprite atlas");
    }

    @Inject(method = "load(Lnet/minecraft/resource/ResourceManager;)V", at = @At("RETURN"))
    private void endReload(ResourceManager resourceManager_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

    @Inject(method = "loadSprite(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/client/texture/Sprite;)Z", at = @At("HEAD"))
    private void startLoadSprite(ResourceManager resourceManager_1, Sprite sprite_1, CallbackInfoReturnable<Boolean> cir) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName(String.format("Loading sprite '%s'", sprite_1.getId()));
    }

    @Inject(method = "loadSprite(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/client/texture/Sprite;)Z", at = @At("RETURN"))
    private void endLoadSprite(ResourceManager resourceManager_1, Sprite sprite_1, CallbackInfoReturnable<Boolean> cir) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

}