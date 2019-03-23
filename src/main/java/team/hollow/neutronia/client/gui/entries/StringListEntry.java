package team.hollow.neutronia.client.gui.entries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import team.hollow.neutronia.client.gui.ModConfigScreen;
import team.hollow.neutronia.config.types.StringConfig;

@Environment(EnvType.CLIENT)
public class StringListEntry extends TextFieldListEntry<StringConfig> {

    public StringListEntry(ModConfigScreen parent, ConfigListWidget parentList, StringConfig config) {
        super(parent, parentList, config);
    }

    @Override
    public void save() {
        this.getConfigEntry().setConfigValue(this.textFieldWidget.getText());
    }
}
