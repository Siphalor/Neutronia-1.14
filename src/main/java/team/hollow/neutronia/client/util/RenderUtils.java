package team.hollow.neutronia.client.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.text.TextComponent;

@Environment(EnvType.CLIENT)
public class RenderUtils {
	public static MinecraftClient client = MinecraftClient.getInstance();

	public static void drawBadge(int x, int y, int tagWidth, TextComponent text, int outlineColor, int fillColor, int textColor) {
		DrawableHelper.fill(x + 1, y - 1, x + tagWidth, y, outlineColor);
		DrawableHelper.fill(x, y, x + 1, y + client.textRenderer.fontHeight, outlineColor);
		DrawableHelper.fill(x + 1, y + 1 + client.textRenderer.fontHeight - 1, x + tagWidth, y + client.textRenderer.fontHeight + 1, outlineColor);
		DrawableHelper.fill(x + tagWidth, y, x + tagWidth + 1, y + client.textRenderer.fontHeight, outlineColor);
		DrawableHelper.fill(x + 1, y, x + tagWidth, y + client.textRenderer.fontHeight, fillColor);
		client.textRenderer.draw(text.getFormattedText(), (x + 1 + ((tagWidth - client.textRenderer.getStringWidth(text.getFormattedText())) >> 1)), y + 1, textColor);
	}
}