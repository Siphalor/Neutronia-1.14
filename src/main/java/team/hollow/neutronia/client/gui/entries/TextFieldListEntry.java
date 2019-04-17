//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui.entries;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.Window;
import team.hollow.neutronia.client.gui.ClothConfigScreen;
import team.hollow.neutronia.hooks.TextFieldWidgetHooks;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class TextFieldListEntry<T> extends ClothConfigScreen.ListEntry {
    protected TextFieldWidget textFieldWidget;
    protected ButtonWidget resetButton;
    protected Supplier<T> defaultValue;
    protected T original;
    protected List<Element> widgets;

    protected TextFieldListEntry(String fieldName, T original, String resetButtonKey, Supplier<T> defaultValue) {
        super(fieldName);
        this.defaultValue = defaultValue;
        this.original = original;
        this.textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 148, 18, String.valueOf(defaultValue.get()));
        this.textFieldWidget.setMaxLength(999999);
        this.textFieldWidget.setText(String.valueOf(original));
        this.textFieldWidget.setChangedListener((s) -> {
            if (!original.equals(s)) {
                this.getScreen().setEdited(true);
            }

        });
        this.widgets = Lists.newArrayList(new Element[]{this.textFieldWidget, this.resetButton});
    }

    protected static void setTextFieldWidth(TextFieldWidget widget, int width) {
        ((TextFieldWidgetHooks)widget).neutronia_setWidth(width);
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        Window window = MinecraftClient.getInstance().window;
        this.resetButton.active = this.getDefaultValue().isPresent() && !this.isMatchDefault(this.textFieldWidget.getText());
        this.resetButton.y = y;
        ((TextFieldWidgetHooks)this.textFieldWidget).neutronia_setY(y + 1);
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName()), (float)(window.getScaledWidth() - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(this.getFieldName()))), (float)(y + 5), 16777215);
            this.resetButton.x = x;
            this.textFieldWidget.setX(x + this.resetButton.getWidth() + 2);
            setTextFieldWidth(this.textFieldWidget, 150 - this.resetButton.getWidth() - 4);
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(this.getFieldName()), (float)x, (float)(y + 5), 16777215);
            this.resetButton.x = window.getScaledWidth() - x - this.resetButton.getWidth();
            this.textFieldWidget.setX(window.getScaledWidth() - x - 150);
            setTextFieldWidth(this.textFieldWidget, 150 - this.resetButton.getWidth() - 4);
        }

        this.resetButton.render(mouseX, mouseY, delta);
        this.textFieldWidget.render(mouseX, mouseY, delta);
    }

    protected abstract boolean isMatchDefault(String var1);

    public Optional<Object> getDefaultValue() {
        return this.defaultValue == null ? Optional.empty() : Optional.ofNullable(this.defaultValue.get());
    }

    public String getYesNoText(boolean bool) {
        return bool ? "§aYes" : "§cNo";
    }

    public List<? extends Element> children() {
        return this.widgets;
    }
}
