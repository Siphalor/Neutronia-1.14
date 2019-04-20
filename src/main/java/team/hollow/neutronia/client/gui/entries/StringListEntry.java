//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class StringListEntry extends TextFieldListEntry<String> {
    private Consumer<String> saveConsumer;

    public StringListEntry(String fieldName, String value, Consumer<String> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", null, saveConsumer);
    }

    public StringListEntry(String fieldName, String value, String resetButtonKey, Supplier<String> defaultValue, Consumer<String> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.saveConsumer = saveConsumer;
    }

    public String getObject() {
        return this.textFieldWidget.getText();
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() && text.equals(this.getDefaultValue().get());
    }
}
