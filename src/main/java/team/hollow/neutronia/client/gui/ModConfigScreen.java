package team.hollow.neutronia.client.gui;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableTextComponent;
import team.hollow.neutronia.client.ClientUtil;
import team.hollow.neutronia.client.gui.entries.AbstractListEntry;
import team.hollow.neutronia.client.gui.entries.ConfigListWidget;
import team.hollow.neutronia.config.ConfigFile;
import team.hollow.neutronia.config.types.ConfigField;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ModConfigScreen extends Screen {
    private Screen parent;
    private ConfigFile config;
    private boolean hasChanges;
    private ConfigListWidget configListWidget;

    public ModConfigScreen(Screen parent, ModMetadata modMetadata, ConfigFile config) {
        super(new TranslatableTextComponent("screen.knit.mod.config", modMetadata.getName()));
        this.parent = parent;
        this.config = config;
    }

    @Override
    protected void onInitialized() {
        super.onInitialized();
        this.configListWidget = new ConfigListWidget(this, client);
        this.listeners.add(this.configListWidget);
        this.focusOn(this.configListWidget);
        this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, this.screenHeight - 25, 200, 20,
                I18n.translate("gui.done"), (buttonWidget) -> {
            if (this.hasChanges) {
                List<ConfigField> changes = Lists.newArrayList();
                for (AbstractListEntry entry : this.configListWidget.getInputListeners()) {
                    entry.save();
                    changes.add(entry.getConfigEntry());
                }
                config.readFromArrays(changes);
            }
            this.client.openScreen(parent);
        }));
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        this.drawBackground();
        this.focusOn(this.configListWidget);
        this.configListWidget.render(int_1, int_2, float_1);
        this.drawStringCentered(this.fontRenderer, this.title.getFormattedText(), this.screenWidth / 2, 15, 0xffffff);
        super.render(int_1, int_2, float_1);
        this.drawStringCentered(this.fontRenderer, I18n.translate("screen.knit.mod.config.tooltip"),
                this.screenWidth / 2, this.screenHeight - 35, 0xffff00);

        if (this.configListWidget.getLastHoveredEntry() != null) {
            String comment = this.configListWidget.getLastHoveredEntry().getConfigEntry().getComment();
            if (!Strings.isNullOrEmpty(comment) && ClientUtil.safeKeyDown(ClientUtil.LEFT_SHIFT))
                this.drawTooltip(client.textRenderer.wrapStringToWidthAsList(comment, 150), int_1, int_2);
        }
    }

    public void setHasChanges(boolean val) {
        this.hasChanges = val;
    }

    public ConfigFile getConfig() {
        return config;
    }
}
