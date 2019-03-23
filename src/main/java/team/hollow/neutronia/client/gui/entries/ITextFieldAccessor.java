package team.hollow.neutronia.client.gui.entries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface ITextFieldAccessor {
    void setWidgetY(int y);

    int getWidgetWidth();
}
