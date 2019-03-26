package team.hollow.neutronia.client.gui.entries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import team.hollow.neutronia.client.gui.ModConfigScreen;
import team.hollow.neutronia.config.types.*;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ConfigListWidget extends EntryListWidget<AbstractListEntry<?>> {

    private AbstractListEntry<?> lastHoveredEntry;

    public ConfigListWidget(ModConfigScreen modConfigScreen, MinecraftClient client) {
        super(client, modConfigScreen.width, modConfigScreen.height , 30,
                modConfigScreen.height - 40, 25);
        for (Map.Entry<String, ConfigField> entry : modConfigScreen.getConfig().getConfigValues().entrySet()) {
            ConfigField field = entry.getValue();
            if (field instanceof IntConfig) {
                this.addEntry(new IntListEntry(modConfigScreen, this, (IntConfig) field));
            } else if (field instanceof BooleanConfig) {
                this.addEntry(new BooleanListEntry(modConfigScreen, this, (BooleanConfig) field));
            } else if (field instanceof NumberConfig) {
                this.addEntry(new NumberListEntry(modConfigScreen, this, (NumberConfig) field));
            } else if (field instanceof StringConfig) {
                this.addEntry(new StringListEntry(modConfigScreen, this, (StringConfig) field));
            }
        }
    }

    @Override
    protected int getMaxPosition() {
        return width - 50;
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        super.render(int_1, int_2, float_1);
    }

    @Override
    protected int getScrollbarPosition() {
        return width - 10;
    }

    public AbstractListEntry<?> getLastHoveredEntry() {
        return lastHoveredEntry;
    }

    void setLastHoveredEntry(AbstractListEntry<?> lastHoveredEntry) {
        this.lastHoveredEntry = lastHoveredEntry;
    }
}
