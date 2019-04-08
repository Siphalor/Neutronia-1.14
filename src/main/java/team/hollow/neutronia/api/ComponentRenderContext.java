package team.hollow.neutronia.api;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * A context for a custom component's methods.
 */
public interface ComponentRenderContext {

	public Screen getGui();

	public TextRenderer getFont();

	public void renderItemStack(int x, int y, int mouseX, int mouseY, ItemStack stack);

	public void renderIngredient(int x, int y, int mouseX, int mouseY, Ingredient ingredient);

	public boolean isAreaHovered(int mouseX, int mouseY, int x, int y, int w, int h);
	
	public void setHoverTooltip(List<String> tooltip);
	
	public void registerButton(ButtonWidget button, int pageNum, Runnable onClick);

	public Identifier getBookTexture();
	
	public Identifier getCraftingTexture();
	
	public int getTextColor();
	
	public int getHeaderColor();
	
}