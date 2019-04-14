//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;
import net.minecraft.util.math.MathHelper;
import team.hollow.neutronia.client.gui.ClothConfigScreen;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class IntegerSliderEntry extends ClothConfigScreen.ListEntry {
    protected IntegerSliderEntry.Slider sliderWidget;
    protected ButtonWidget resetButton;
    protected AtomicInteger value;
    private int minimum;
    private int maximum;
    private Consumer<Integer> saveConsumer;
    private Supplier<Integer> defaultValue;
    private Function<Integer, String> textGetter;
    private List<Element> widgets;

    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, Consumer<Integer> saveConsumer) {
        this(fieldName, minimum, maximum, value, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public IntegerSliderEntry(String fieldName, int minimum, int maximum, int value, String resetButtonKey, Supplier<Integer> defaultValue, Consumer<Integer> saveConsumer) {
        super(fieldName);
        this.textGetter = (integer) -> {
            return String.format("Value: %d", integer);
        };
        this.defaultValue = defaultValue;
        this.value = new AtomicInteger(value);
        this.saveConsumer = saveConsumer;
        this.maximum = maximum;
        this.minimum = minimum;
        this.sliderWidget = new IntegerSliderEntry.Slider(0, 0, 152, 20, ((double)this.value.get() - (double)minimum) / (double)Math.abs(maximum - minimum));
        this.resetButton = new ButtonWidget(0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey, new Object[0])) + 6, 20, I18n.translate(resetButtonKey, new Object[0]), (widget) -> {
            this.sliderWidget.setProgress((double)(MathHelper.clamp((Integer)this.defaultValue.get(), minimum, maximum) - minimum) / (double)Math.abs(maximum - minimum));
            this.sliderWidget.applyValue();
            this.sliderWidget.updateMessage();
        });
        this.sliderWidget.setMessage((String)this.textGetter.apply(this.value.get()));
        this.widgets = Lists.newArrayList(new Element[]{this.sliderWidget, this.resetButton});
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    public Function<Integer, String> getTextGetter() {
        return this.textGetter;
    }

    public IntegerSliderEntry setTextGetter(Function<Integer, String> textGetter) {
        this.textGetter = textGetter;
        return this;
    }

    public Integer getObject() {
        return this.value.get();
    }

    public Optional<Object> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public List<? extends Element> children() {
        return this.widgets;
    }

    public IntegerSliderEntry setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }

    public IntegerSliderEntry setMinimum(int minimum) {
        this.minimum = minimum;
        return this;
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        Window window = MinecraftClient.getInstance().window;
        this.resetButton.active = this.getDefaultValue().isPresent() && (Integer)this.defaultValue.get() != this.value.get();
        this.resetButton.y = y;
        this.sliderWidget.y = y;
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName(), new Object[0]), (float)(window.getScaledWidth() - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(this.getFieldName(), new Object[0]))), (float)(y + 5), 16777215);
            this.resetButton.x = x;
            this.sliderWidget.x = x + this.resetButton.getWidth() + 1;
            this.sliderWidget.setWidth(150 - this.resetButton.getWidth() - 2);
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName(), new Object[0]), (float)x, (float)(y + 5), 16777215);
            this.resetButton.x = window.getScaledWidth() - x - this.resetButton.getWidth();
            this.sliderWidget.x = window.getScaledWidth() - x - 150;
            this.sliderWidget.setWidth(150 - this.resetButton.getWidth() - 2);
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.sliderWidget.render(mouseX, mouseY, delta);
    }

    private class Slider extends SliderWidget {
        protected Slider(int int_1, int int_2, int int_3, int int_4, double double_1) {
            super(int_1, int_2, int_3, int_4, double_1);
        }

        public void updateMessage() {
            this.setMessage((String)IntegerSliderEntry.this.textGetter.apply(IntegerSliderEntry.this.value.get()));
        }

        protected void applyValue() {
            IntegerSliderEntry.this.value.set((int)((double)IntegerSliderEntry.this.minimum + (double)Math.abs(IntegerSliderEntry.this.maximum - IntegerSliderEntry.this.minimum) * this.value));
            IntegerSliderEntry.this.getScreen().setEdited(true);
        }

        public double getProgress() {
            return this.value;
        }

        public void setProgress(double integer) {
            this.value = integer;
        }
    }
}
