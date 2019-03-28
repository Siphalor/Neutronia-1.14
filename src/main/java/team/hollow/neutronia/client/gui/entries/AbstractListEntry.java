package team.hollow.neutronia.client.gui.entries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.EntryListWidget;
import team.hollow.neutronia.client.ClientUtil;
import team.hollow.neutronia.client.gui.ModConfigScreen;
import team.hollow.neutronia.config.types.ConfigField;

import java.awt.*;

@Environment(EnvType.CLIENT)
public abstract class AbstractListEntry<T extends ConfigField> extends EntryListWidget.Entry<AbstractListEntry<?>> {
    protected Object originalValue;
    private ConfigListWidget parentList;
    private T configEntry;
    private ModConfigScreen parent;

    public AbstractListEntry(ModConfigScreen parent, ConfigListWidget parentList, T configEntry) {
        this.parent = parent;
        this.parentList = parentList;
        this.configEntry = configEntry;
        this.originalValue = configEntry.getConfigValue();
    }

    @Override
    public void draw(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, float var9) {
        Point point = ClientUtil.getMousePoint();
        if (this.isMouseOver(point.x, point.y))
            parentList.setLastHoveredEntry(this);
        else if (parentList.getLastHoveredEntry() == this)
            parentList.setLastHoveredEntry(null);
    }

    public T getConfigEntry() {
        return configEntry;
    }

    public ModConfigScreen getParentScreen() {
        return parent;
    }

    public abstract void save();
}
