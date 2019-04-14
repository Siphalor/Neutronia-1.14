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

public class LongListEntry extends TextFieldListEntry<Long> {
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
    private long minimum;
    private long maximum;
    private Consumer<Long> saveConsumer;

    public LongListEntry(String fieldName, Long value, Consumer<Long> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public LongListEntry(String fieldName, Long value, String resetButtonKey, Supplier<Long> defaultValue, Consumer<Long> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.minimum = -9223372036854775807L;
        this.maximum = 9223372036854775807L;
        this.textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 148, 18) {
            public void addText(String string_1) {
                super.addText((String)LongListEntry.stripCharacters.apply(string_1));
            }

            public void render(int int_1, int int_2, float float_1) {
                try {
                    long i = Long.valueOf(LongListEntry.this.textFieldWidget.getText());
                    if (i >= LongListEntry.this.minimum && i <= LongListEntry.this.maximum) {
                        this.method_1868(14737632);
                    } else {
                        this.method_1868(16733525);
                    }
                } catch (NumberFormatException var6) {
                    this.method_1868(16733525);
                }

                super.render(int_1, int_2, float_1);
            }
        };
        this.textFieldWidget.setText(String.valueOf(value));
        this.textFieldWidget.setMaxLength(999999);
        this.textFieldWidget.setChangedListener((s) -> {
            if (!((Long)this.original).equals(s)) {
                this.getScreen().setEdited(true);
            }

        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new Element[]{this.textFieldWidget, this.resetButton});
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() ? text.equals(((Long)this.defaultValue.get()).toString()) : false;
    }

    public LongListEntry setMinimum(long minimum) {
        this.minimum = minimum;
        return this;
    }

    public LongListEntry setMaximum(long maximum) {
        this.maximum = maximum;
        return this;
    }

    public Long getObject() {
        try {
            return Long.valueOf(this.textFieldWidget.getText());
        } catch (Exception var2) {
            return 0L;
        }
    }

    public Optional<String> getError() {
        try {
            long i = Long.valueOf(this.textFieldWidget.getText());
            if (i > this.maximum) {
                return Optional.of(I18n.translate("text.cloth-config.error.too_large", new Object[]{this.maximum}));
            }

            if (i < this.minimum) {
                return Optional.of(I18n.translate("text.cloth-config.error.too_small", new Object[]{this.minimum}));
            }
        } catch (NumberFormatException var3) {
            return Optional.of(I18n.translate("text.cloth-config.error.not_valid_number_long", new Object[0]));
        }

        return super.getError();
    }
}
