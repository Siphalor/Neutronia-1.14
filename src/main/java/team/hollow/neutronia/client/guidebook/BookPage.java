package team.hollow.neutronia.client.guidebook;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import team.hollow.neutronia.client.guidebook.gui.GuiBookEntry;
import team.hollow.neutronia.notebook.Notebook;

import java.util.ArrayList;
import java.util.List;

public abstract class BookPage {

    public transient MinecraftClient mc;
    public transient TextRenderer fontRenderer;
    public transient GuiBookEntry parent;

    public transient Notebook book;
    public transient int left, top;
    public transient JsonObject sourceObject;
    protected transient BookEntry entry;
    protected transient int pageNum;
    protected String type, flag, advancement, anchor;
    private transient List<ButtonWidget> buttons;

    public void build(BookEntry entry, int pageNum) {
        this.book = entry.book;
        this.entry = entry;
        this.pageNum = pageNum;
    }

    public void onDisplayed(GuiBookEntry parent, int left, int top) {
        mc = parent.getMinecraft();
        book = parent.book;
        fontRenderer = mc.textRenderer;
        this.parent = parent;
        this.left = left;
        this.top = top;
        buttons = new ArrayList<>();
    }

    public void onHidden(GuiBookEntry parent) {
        parent.getButtonList().removeAll(buttons);
    }

    protected void addButton(ButtonWidget button) {
        button.x += (parent.bookLeft + left);
        button.y += (parent.bookTop + top);
        buttons.add(button);
        parent.getButtonList().add(button);
    }

    public void render(int mouseX, int mouseY, float pticks) {
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    protected void onButtonClicked(ButtonWidget button) {
    }

    public final boolean interceptButton(ButtonWidget button) {
        if (buttons.contains(button)) {
            onButtonClicked(button);
            return true;
        }

        return false;
    }

}