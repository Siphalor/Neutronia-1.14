package team.hollow.modmenu_api.api;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class ModMenuBadge {

    public Component text;
    public int outlineColor, fillColor;

    public ModMenuBadge(String text, int outlineColor, int fillColor) {
        this.text = new TranslatableComponent(text);
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

}
