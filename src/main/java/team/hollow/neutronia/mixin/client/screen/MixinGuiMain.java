package team.hollow.neutronia.mixin.client.screen;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.MainMenuScreen;
import net.minecraft.client.gui.Screen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.Neutronia;

@Mixin(value = MainMenuScreen.class, remap = false)
public abstract class MixinGuiMain extends Screen {

    private static final Identifier LOGO = new Identifier(Neutronia.MOD_ID, "textures/minecraft_replacement.png");

    @Inject(method = "draw(IIF)V", at = @At("RETURN"))
    public void fabricInfo(int mouseX, int mouseY, float delta, CallbackInfo info) {
        this.fontRenderer.draw("Fabric Loader Version: v0.3.5.106", 2, this.screenHeight - 40, 0xFFFFFF);
        this.fontRenderer.draw("Fabric API Version: v0.2.0.89", 2, this.screenHeight - 30, 0xFFFFFF);
        this.fontRenderer.draw("Loaded mods: " + FabricLoader.getInstance().getAllMods().size(), 2, this.screenHeight - 20, 0xFFFFFF);
        int int_4 = this.screenWidth / 2 - 137;
        this.client.getTextureManager().bindTexture(LOGO);
        drawTexturedRect(int_4, 30, 0, 0, 800, 225, 155, 44);
    }

}