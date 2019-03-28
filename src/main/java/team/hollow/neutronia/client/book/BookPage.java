package team.hollow.neutronia.client.book;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.widget.ButtonWidget;
import team.hollow.neutronia.book.Book;
import team.hollow.neutronia.client.book.gui.GuiBookEntry;
import vazkii.patchouli.client.base.ClientAdvancements;
import vazkii.patchouli.common.base.PatchouliConfig;
import vazkii.patchouli.common.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BookPage {

	public transient MinecraftClient mc;
	public transient TextRenderer fontRenderer;
	public transient GuiBookEntry parent;

	public transient Book book;
	protected transient BookEntry entry;
	protected transient int pageNum;
	private transient List<ButtonWidget> buttons;
	public transient int left, top;
	public transient JsonObject sourceObject;
	
	protected String type, flag, advancement, anchor;
	
	public void build(BookEntry entry, int pageNum) {
		this.book = entry.book;
		this.entry = entry;
		this.pageNum = pageNum;
		ValidationUtils.validateAdvancement(this.advancement);
	}
	
	public void onDisplayed(GuiBookEntry parent, int left, int top) { 
		mc = parent.mc;
		book = parent.book;
		fontRenderer = mc.textRenderer;
		this.parent = parent;
		this.left = left;
		this.top = top;
		buttons = new ArrayList<>();
	}

	public boolean isPageUnlocked() {
		return advancement == null || advancement.isEmpty() || ClientAdvancements.hasDone(advancement);
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
	
	public void render(int mouseX, int mouseY, float pticks) { }
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) { }
	protected void onButtonClicked(ButtonWidget button) { }
	
	public final boolean interceptButton(ButtonWidget button) {
		if(buttons.contains(button)) {
			onButtonClicked(button);
			return true;
		}
		
		return false;
	}
	
	public boolean canAdd(Book book) {
		return flag == null || flag.isEmpty() || PatchouliConfig.getConfigFlag(flag);
	}
	
}
