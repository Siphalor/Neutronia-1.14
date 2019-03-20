package team.hollow.neutronia.mixin.client.screen;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.MainMenuScreen;
import net.minecraft.client.gui.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MainMenuScreen.class)
public abstract class MixinGuiMain extends Screen {

    @Inject(method = "draw(IIF)V", at = @At("RETURN"))
    public void fabricInfo(int mouseX, int mouseY, float delta, CallbackInfo info) {
        /*ShaderEffectManager.getInstance()
                .manage(new Identifier("minecraft", "shaders/post/creeper.json")).render(delta);
        ShaderEffectManager.getInstance()
                .manage(new Identifier("minecraft", "shaders/post/art.json")).render(delta);
        ShaderEffectManager.getInstance()
                .manage(new Identifier("minecraft", "shaders/post/flip.json")).render(delta);*/
        this.fontRenderer.draw("Fabric Loader Version: v0.3.5.109", 2, this.screenHeight - 40, 0xFFFFFF);
        this.fontRenderer.draw("Fabric API Version: v0.2.0.107", 2, this.screenHeight - 30, 0xFFFFFF);
        this.fontRenderer.draw("Loaded mods: " + FabricLoader.getInstance().getAllMods().size(), 2, this.screenHeight - 20, 0xFFFFFF);
    }

}