package team.hollow.neutronia.mixin.client.screen;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.MainMenuScreen;
import net.minecraft.client.gui.Screen;
import net.minecraft.text.TranslatableTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.Neutronia;

import java.util.Objects;

@Mixin(value = MainMenuScreen.class)
public abstract class MixinGuiMain extends Screen {

    protected MixinGuiMain() {
        super(new TranslatableTextComponent("narrator.screen.title"));
    }

    @Inject(method = "render(IIF)V", at = @At("RETURN"))
    public void fabricInfo(int mouseX, int mouseY, float delta, CallbackInfo info) {
        if (Neutronia.testConfig.client.mainMenuExtra) {
            Objects.requireNonNull(this.minecraft).textRenderer.draw("Fabric Loader Version: v0.3.5.109", 2, this.height - 40, 0xFFFFFF);
            this.minecraft.textRenderer.draw("Fabric API Version: v0.2.0.107", 2, this.height - 30, 0xFFFFFF);
            this.minecraft.textRenderer.draw("Loaded mods: " + FabricLoader.getInstance().getAllMods().size(), 2, this.height - 20, 0xFFFFFF);
        }
    }

}