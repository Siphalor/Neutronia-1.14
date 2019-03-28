package team.hollow.neutronia.client.book.gui.button;

import com.google.common.base.Supplier;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.gui.widget.ButtonWidget;
import team.hollow.neutronia.client.book.gui.GuiBook;

import java.util.Arrays;
import java.util.List;

public class GuiButtonBook extends ButtonWidget {

	GuiBook parent;
	int u, v;
	Supplier<Boolean> displayCondition;
	List<String> tooltip;
	
	public GuiButtonBook(GuiBook parent, int x, int y, int u, int v, int w, int h, String... tooltip) {
		this(parent, x, y, u, v, w, h, ()->true, tooltip);
	}
	
	public GuiButtonBook(GuiBook parent, int x, int y, int u, int v, int w, int h, Supplier<Boolean> displayCondition, String... tooltip) {
		super(x, y, w, h, "", var1 -> {

		});
		this.parent = parent;
		this.u = u;
		this.v = v;
		this.displayCondition = displayCondition;
		this.tooltip = Arrays.asList(tooltip);
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.color3f(1F, 1F, 1F);
		this.active = visible = displayCondition.get();
		hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
		
		if(visible) {
			GuiBook.drawFromTexture(parent.book, x, y, u + (hovered ? width : 0), v, width, height);
			if(hovered)
				parent.setTooltip(getTooltip());
		}
	}
	
	@Override
    public void playDownSound(SoundManager soundHandlerIn) {
		GuiBook.playBookFlipSound(parent.book);
	}
	
	public List<String> getTooltip() {
		return tooltip;
	}

}
