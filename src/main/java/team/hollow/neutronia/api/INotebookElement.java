package team.hollow.neutronia.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Screen;

public interface INotebookElement {
    @Environment(EnvType.CLIENT)
    int draw(Screen screen, int x, int y, int mouseX, int mouseY, int xTop, int yTop);

    @Environment(EnvType.CLIENT)
    default void drawOverlay(Screen screen, int mouseX, int mouseY, int xTop, int yTop) {

    }

    @Environment(EnvType.CLIENT)
    default boolean mouseOver(int mouseX, int mouseY) {
        return false;
    }

    @Environment(EnvType.CLIENT)
    default INotebookSection handleClick(int mouseX, int mouseY) {
        return null;
    }
}