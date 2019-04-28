package team.hollow.neutronia.mixin.client.progress;

import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import team.hollow.neutronia.LoadingProgressImpl;

import java.util.Iterator;

@Mixin(value = SpriteAtlasTexture.class)
public abstract class MixinSpriteAtlasTexture {

    @Inject(method = "upload(Lnet/minecraft/client/texture/SpriteAtlasTexture$Data;)V", at = @At("HEAD"))
    private void startReload(SpriteAtlasTexture.Data spriteAtlasTexture$class_4007_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName("Building sprite atlas");
    }

    @Inject(method = "upload(Lnet/minecraft/client/texture/SpriteAtlasTexture$Data;)V", at = @At("RETURN"))
    private void endReload(SpriteAtlasTexture.Data spriteAtlasTexture$class_4007_1, CallbackInfo ci) {
        LoadingProgressImpl.INSTANCE.popTask();
    }

    @Inject(method = "upload(Lnet/minecraft/client/texture/SpriteAtlasTexture$Data;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void startAddSprite(SpriteAtlasTexture.Data spriteAtlasTexture$class_4007_1, CallbackInfo ci, Iterator var2, Sprite sprite_1) {
        LoadingProgressImpl.INSTANCE.pushTask().withTaskName(String.format("Adding sprite '%s'", sprite_1.getId()));
    }

    @Inject(method = "upload(Lnet/minecraft/client/texture/SpriteAtlasTexture$Data;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/Sprite;isAnimated()Z", shift = At.Shift.BEFORE))
    private void endAddSprite(SpriteAtlasTexture.Data spriteAtlasTexture$class_4007_1, CallbackInfo ci) {
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