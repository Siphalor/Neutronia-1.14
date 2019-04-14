//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;
import team.hollow.neutronia.client.gui.ClothConfigScreen;

public class BooleanListEntry extends ClothConfigScreen.ListEntry {
    private AtomicBoolean bool;
    private ButtonWidget buttonWidget;
    private ButtonWidget resetButton;
    private Consumer<Boolean> saveConsumer;
    private Supplier<Boolean> defaultValue;
    private List<Element> widgets;

    public BooleanListEntry(String fieldName, boolean bool, Consumer<Boolean> saveConsumer) {
        this(fieldName, bool, "text.cloth-config.reset_value", (Supplier)null, saveConsumer);
    }

    public BooleanListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer) {
        super(fieldName);
        this.defaultValue = defaultValue;
        this.bool = new AtomicBoolean(bool);
        this.buttonWidget = new ButtonWidget(0, 0, 150, 20, "", (widget) -> {
            this.bool.set(!this.bool.get());
            this.getScreen().setEdited(true);
        });
        this.resetButton = new ButtonWidget(0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey, new Object[0])) + 6, 20, I18n.translate(resetButtonKey, new Object[0]), (widget) -> {
            this.bool.set((Boolean)defaultValue.get());
            this.getScreen().setEdited(true);
        });
        this.saveConsumer = saveConsumer;
        this.widgets = Lists.newArrayList(new Element[]{this.buttonWidget, this.resetButton});
    }

    public void save() {
        if (this.saveConsumer != null) {
            this.saveConsumer.accept(this.getObject());
        }

    }

    public Boolean getObject() {
        return this.bool.get();
    }

    public Optional<Object> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        Window window = MinecraftClient.getInstance().window;
        this.resetButton.active = this.getDefaultValue().isPresent() && (Boolean)this.defaultValue.get() != this.bool.get();
        this.resetButton.y = y;
        this.buttonWidget.y = y;
        this.buttonWidget.setMessage(this.getYesNoText(this.bool.get()));
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName(), new Object[0]), (float)(window.getScaledWidth() - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(this.getFieldName(), new Object[0]))), (float)(y + 5), 16777215);
            this.resetButton.x = x;
            this.buttonWidget.x = x + this.resetButton.getWidth() + 2;
            this.buttonWidget.setWidth(150 - this.resetButton.getWidth() - 2);
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName(), new Object[0]), (float)x, (float)(y + 5), 16777215);
            this.resetButton.x = window.getScaledWidth() - x - this.resetButton.getWidth();
            this.buttonWidget.x = window.getScaledWidth() - x - 150;
            this.buttonWidget.setWidth(150 - this.resetButton.getWidth() - 2);
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.buttonWidget.render(mouseX, mouseY, delta);
    }

    public String getYesNoText(boolean bool) {
        return bool ? "§aYes" : "§cNo";
    }

    public List<? extends Element> children() {
        return this.widgets;
    }
}
