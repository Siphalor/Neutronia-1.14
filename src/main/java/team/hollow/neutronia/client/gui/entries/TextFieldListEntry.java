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
        this.textFieldWidget = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, 0, 0, 148, 18, "") {
            @Override
            public void render(int int_1, int int_2, float float_1) {
                boolean f = isFocused();
                setFocused(TextFieldListEntry.this.getParent().getFocused() == TextFieldListEntry.this && TextFieldListEntry.this.getFocused() == this);
                textFieldPreRender(this);
                super.render(int_1, int_2, float_1);
                setFocused(f);
            }

            @Override
            public void addText(String string_1) {
                super.addText(stripAddText(string_1));
            }
        };
        this.textFieldWidget.setMaxLength(999999);
        this.textFieldWidget.setText(String.valueOf(original));
        this.textFieldWidget.setChangedListener((s) -> {
            if (!original.equals(s)) {
                this.getScreen().setEdited(true);
            }

        });
        this.resetButton = new ButtonWidget(0, 0, MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(resetButtonKey)) + 6, 20, I18n.translate(resetButtonKey), widget -> {
            TextFieldListEntry.this.textFieldWidget.setText(String.valueOf(defaultValue.get()));
            getScreen().setEdited(true);
        });
        this.widgets = Lists.newArrayList(new Element[]{this.textFieldWidget, this.resetButton});
    }

    protected static void setTextFieldWidth(TextFieldWidget widget, int width) {
        widget.setWidth(width);
    }

    protected String stripAddText(String s) {
        return s;
    }

    protected void textFieldPreRender(TextFieldWidget widget) {

    }

    @Override
    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        Window window = MinecraftClient.getInstance().window;
        this.resetButton.active = getDefaultValue().isPresent() && !isMatchDefault(textFieldWidget.getText());
        this.resetButton.y = y;
        this.textFieldWidget.y = y + 1;
        if (MinecraftClient.getInstance().textRenderer.isRightToLeft()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), window.getScaledWidth() - x - MinecraftClient.getInstance().textRenderer.getStringWidth(I18n.translate(getFieldName())), y + 5, 16777215);
            this.resetButton.x = x;
            this.textFieldWidget.setX(x + resetButton.getWidth() + 2);
            setTextFieldWidth(textFieldWidget, 150 - resetButton.getWidth() - 4);
        } else {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(I18n.translate(getFieldName()), x, y + 5, 16777215);
            this.resetButton.x = window.getScaledWidth() - x - resetButton.getWidth();
            this.textFieldWidget.setX(window.getScaledWidth() - x - 150);
            setTextFieldWidth(textFieldWidget, 150 - resetButton.getWidth() - 4);
        }
        resetButton.render(mouseX, mouseY, delta);
        textFieldWidget.render(mouseX, mouseY, delta);
    }

    protected abstract boolean isMatchDefault(String text);

    @Override
    public Optional<Object> getDefaultValue() {
        return defaultValue == null ? Optional.empty() : Optional.ofNullable(defaultValue.get());
    }

    @Override
    public List<? extends Element> children() {
        return widgets;
    }
}
