package team.hollow.modmenu_api.api;

import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;

public class ModMenuBadge {

    public TextComponent text;
    public int outlineColor, fillColor;

    public ModMenuBadge(String text, int outlineColor, int fillColor) {
        this.text = new TranslatableTextComponent(text);
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

}
