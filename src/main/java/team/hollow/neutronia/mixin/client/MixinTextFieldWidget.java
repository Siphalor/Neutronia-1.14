package team.hollow.neutronia.mixin.client;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import team.hollow.neutronia.hooks.TextFieldWidgetHooks;

@Mixin({TextFieldWidget.class})
public class MixinTextFieldWidget implements TextFieldWidgetHooks {
    @Shadow
    private int y;
    @Shadow
    @Mutable
    @Final
    private int width;

    public MixinTextFieldWidget() {
    }

    public void clothconfig_setY(int y) {
        this.y = y;
    }

    public int clothconfig_getWidth() {
        return this.width;
    }

    public void clothconfig_setWidth(int width) {
        this.width = width;
    }
}