package team.hollow.neutronia.mixin.client;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.menu.SettingsScreen;
import net.minecraft.text.TranslatableTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SettingsScreen.class)
public abstract class SettingScreenMixin extends Screen {

    public SettingScreenMixin() {
        super(new TranslatableTextComponent("options.title"));
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo callback) {
        /*if (Neutronia.testConfig.client.displayConfigButton && FabricLoader.getInstance().isModLoaded("cloth")) {
            this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20,
                    I18n.translate("screen.knit.mod.config.edit"), buttonWidget ->
                    Neutronia.openConfigScreen(this)));
        }*/
    }
}
