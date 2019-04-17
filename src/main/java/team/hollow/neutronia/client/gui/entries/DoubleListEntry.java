//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DoubleListEntry extends TextFieldListEntry<Double> {
    private static Function<String, String> stripCharacters = (s) -> {
        StringBuilder stringBuilder_1 = new StringBuilder();
        char[] var2 = s.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            if (Character.isDigit(var2[var4]) || var2[var4] == '-' || var2[var4] == '.') {
                stringBuilder_1.append(var2[var4]);
            }
        }

        return stringBuilder_1.toString();
    };
    private double minimum;
    private double maximum;
    private Consumer<Double> saveConsumer;

    public DoubleListEntry(String fieldName, Double value, Consumer<Double> saveConsumer) {
        this(fieldName, value, "text.cloth-config.reset_value", null, saveConsumer);
    }

    public DoubleListEntry(String fieldName, Double value, String resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer) {
        super(fieldName, value, resetButtonKey, defaultValue);
        this.minimum = -1.7976931348623157E308D;
        this.maximum = 1.7976931348623157E308D;
        this.textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 148, 18, String.valueOf(defaultValue.get())) {
            public void addText(String string_1) {
                super.addText(DoubleListEntry.stripCharacters.apply(string_1));
            }

            public void render(int int_1, int int_2, float float_1) {
                try {
                    double i = Double.valueOf(DoubleListEntry.this.textFieldWidget.getText());
                    if (i >= DoubleListEntry.this.minimum && i <= DoubleListEntry.this.maximum) {
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
        this.saveConsumer = saveConsumer;
        this.textFieldWidget.setText(String.valueOf(value));
        this.textFieldWidget.setMaxLength(999999);
        this.textFieldWidget.setChangedListener((s) -> {
            if (!this.original.equals(s)) {
                this.getScreen().setEdited(true);
            }

        });
        this.widgets = Lists.newArrayList(new Element[]{this.textFieldWidget, this.resetButton});
    }

    protected boolean isMatchDefault(String text) {
        return this.getDefaultValue().isPresent() && text.equals(this.defaultValue.get().toString());
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    public DoubleListEntry setMinimum(double minimum) {
        this.minimum = minimum;
        return this;
    }

    public DoubleListEntry setMaximum(double maximum) {
        this.maximum = maximum;
        return this;
    }

    public Double getObject() {
        try {
            return Double.valueOf(this.textFieldWidget.getText());
        } catch (Exception var2) {
            return 0.0D;
        }
    }

    public Optional<String> getError() {
        try {
            double i = Double.valueOf(this.textFieldWidget.getText());
            if (i > this.maximum) {
                return Optional.of(I18n.translate("text.cloth-config.error.too_large", this.maximum));
            }

            if (i < this.minimum) {
                return Optional.of(I18n.translate("text.cloth-config.error.too_small", this.minimum));
            }
        } catch (NumberFormatException var3) {
            return Optional.of(I18n.translate("text.cloth-config.error.not_valid_number_double"));
        }

        return super.getError();
    }
}
