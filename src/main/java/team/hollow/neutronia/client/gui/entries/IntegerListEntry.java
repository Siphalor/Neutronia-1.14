//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import com.google.common.collect.Lists;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;

public class IntegerListEntry extends TextFieldListEntry<Integer> {
    private static Function<String, String> stripCharacters = (s) -> {
        StringBuilder stringBuilder_1 = new StringBuilder();
        char[] var2 = s.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            if (Character.isDigit(var2[var4]) || var2[var4] == '-') {
                stringBuilder_1.append(var2[var4]);
            }
        }

        return stringBuilder_1.toString();
    };
    private int minimum;
    private int maximum;
    private Consumer<Integer> saveConsumer;

    public IntegerListEntry(String fieldName, Integer value, Consumer<Integer> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public IntegerListEntry(String fieldName, Integer value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.minimum = -2147483647;
        this.maximum = 2147483647;
        this.textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 148, 18) {
            public void addText(String string_1) {
                super.addText((String)IntegerListEntry.stripCharacters.apply(string_1));
            }

            public void render(int int_1, int int_2, float float_1) {
                try {
                    int i = Integer.valueOf(IntegerListEntry.this.textFieldWidget.getText());
                    if (i >= IntegerListEntry.this.minimum && i <= IntegerListEntry.this.maximum) {
                        this.method_1868(14737632);
                    } else {
                        this.method_1868(16733525);
                    }
                } catch (NumberFormatException var5) {
                    this.method_1868(16733525);
                }

                super.render(int_1, int_2, float_1);
            }
        };
        this.textFieldWidget.setText(String.valueOf(value));
        this.textFieldWidget.setMaxLength(999999);
        this.textFieldWidget.setChangedListener((s) -> {
            if (!((Integer)this.original).equals(s)) {
                this.getScreen().setEdited(true);
            }

        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new Element[]{this.textFieldWidget, this.resetButton});
    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() ? text.equals(((Integer)this.defaultValue.get()).toString()) : false;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    public IntegerListEntry setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }

    public IntegerListEntry setMinimum(int minimum) {
        this.minimum = minimum;
        return this;
    }

    public Integer getObject() {
        try {
            return Integer.valueOf(this.textFieldWidget.getText());
        } catch (Exception var2) {
            return 0;
        }
    }

    public Optional<String> getError() {
        try {
            int i = Integer.valueOf(this.textFieldWidget.getText());
            if (i > this.maximum) {
                return Optional.of(I18n.translate("text.cloth-config.error.too_large", new Object[]{this.maximum}));
            }

            if (i < this.minimum) {
                return Optional.of(I18n.translate("text.cloth-config.error.too_small", new Object[]{this.minimum}));
            }
        } catch (NumberFormatException var2) {
            return Optional.of(I18n.translate("text.cloth-config.error.not_valid_number_int", new Object[0]));
        }

        return super.getError();
    }
}
