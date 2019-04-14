//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;
import team.hollow.neutronia.client.gui.ClothConfigScreen;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class EnumListEntry<T extends Enum<?>> extends ClothConfigScreen.ListEntry {
    public static final Function<Enum, String> DEFAULT_NAME_PROVIDER = (t) -> {
        return I18n.translate(t instanceof EnumListEntry.Translatable ? ((EnumListEntry.Translatable)t).getKey() : t.toString());
    };
    private ImmutableList<T> values;
    private AtomicInteger index;
    private ButtonWidget buttonWidget;
    private ButtonWidget resetButton;
    private Consumer<T> saveConsumer;
    private Supplier<T> defaultValue;
    private List<Element> widgets;
    private Function<Enum, String> enumNameProvider;

    public EnumListEntry(String fieldName, Class<T> clazz, T value, Consumer<T> saveConsumer) {
        this(fieldName, clazz, value, "text.cloth-config.reset_value", null, saveConsumer);
    }

    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
        this(fieldName, clazz, value, resetButtonKey, defaultValue, saveConsumer, DEFAULT_NAME_PROVIDER);
    }

    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<Enum, String> enumNameProvider) {
        super(fieldName);
        T[] valuesArray = clazz.getEnumConstants();
        if (valuesArray != null) {
            this.values = ImmutableList.copyOf(valuesArray);
        } else {
            this.values = ImmutableList.of(value);
        }

        this.defaultValue = defaultValue;
        this.index = new AtomicInteger(this.values.indexOf(value));
        this.index.compareAndSet(-1, 0);
        this.buttonWidget = new ButtonWidget(0, 0, 150, 20, "", (widget) -> {
            this.index.incrementAndGet();
            this.index.compareAndSet(this.values.size(), 0);
            this.getScreen().setEdited(true);
        });
        this.resetButton = new ButtonWidget(0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey), (widget) -> {
            this.index.set(this.getDefaultIndex());
            this.getScreen().setEdited(true);
        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new Element[]{this.buttonWidget, this.resetButton});
        this.enumNameProvider = enumNameProvider;
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    public T getObject() {
        return this.values.get(this.index.get());
    }

    public Optional<Object> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        Window window = MinecraftClient.getInstance().window;
        this.resetButton.active = this.getDefaultValue().isPresent() && this.getDefaultIndex() != this.index.get();
        this.resetButton.y = y;
        this.buttonWidget.y = y;
        this.buttonWidget.setMessage(this.enumNameProvider.apply(this.getObject()));
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName()), (float)(window.getScaledWidth() - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(this.getFieldName()))), (float)(y + 5), 16777215);
            this.resetButton.x = x;
            this.buttonWidget.x = x + this.resetButton.getWidth() + 2;
            this.buttonWidget.setWidth(150 - this.resetButton.getWidth() - 2);
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName()), (float)x, (float)(y + 5), 16777215);
            this.resetButton.x = window.getScaledWidth() - x - this.resetButton.getWidth();
            this.buttonWidget.x = window.getScaledWidth() - x - 150;
            this.buttonWidget.setWidth(150 - this.resetButton.getWidth() - 2);
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.buttonWidget.render(mouseX, mouseY, delta);
    }

    private int getDefaultIndex() {
        return Math.max(0, this.values.indexOf(this.defaultValue.get()));
    }

    public List<? extends Element> children() {
        return this.widgets;
    }

    public interface Translatable {
        String getKey();
    }
}
