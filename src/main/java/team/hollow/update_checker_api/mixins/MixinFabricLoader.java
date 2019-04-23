package team.hollow.update_checker_api.mixins;

import net.fabricmc.loader.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.update_checker_api.VersionChecker;

import java.io.File;

@Mixin(FabricLoader.class)
public abstract class MixinFabricLoader {

    @Inject(method = "instantiateMods", at = @At("RETURN"))
    public void instantiateMods(File newRunDir, Object gameInstance, CallbackInfo info) {
        VersionChecker.startVersionCheck();
    }

}