package team.hollow.neutronia.client.guidebook.gui;

import net.minecraft.client.font.TextRenderer;
import team.hollow.neutronia.client.guidebook.text.BookTextParser;
import team.hollow.neutronia.client.guidebook.text.Word;
import team.hollow.neutronia.notebook.Notebook;

import java.util.List;

public class BookTextRenderer {
	final Notebook book;
	final GuiBook gui;
	final TextRenderer font;
	final String text;
	final int x, y, width;
	final int spaceWidth;
	final int lineHeight;
	final boolean defaultUnicode;
	final int baseColor;

	List<Word> words;
	
	public BookTextRenderer(GuiBook gui, String text, int x, int y) {
		this(gui, text, x, y, GuiBook.PAGE_WIDTH, GuiBook.TEXT_LINE_HEIGHT, gui.book.textColor);
	}
	
	public BookTextRenderer(GuiBook gui, String text, int x, int y, int width, int lineHeight, int baseColor) {
		this.book = gui.book;
		this.gui = gui;
		this.font = gui.getMinecraft().textRenderer;
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.spaceWidth = font.getStringWidth(" ");
		this.lineHeight = lineHeight;
		this.defaultUnicode = font.isRightToLeft();
		this.baseColor = baseColor;
		
		build();
	}
	
	private void build() {
		BookTextParser parser = new BookTextParser(gui, book, x, y, width, lineHeight, baseColor);
		words = parser.parse(text);
	}
	
	public void render(int mouseX, int mouseY) {
		font.setRightToLeft(true);
		words.forEach(word -> word.render(mouseX, mouseY));
		font.setRightToLeft(defaultUnicode);
	}
	
	public void click(int mouseX, int mouseY, int mouseButton) {
		words.forEach(word -> word.click(mouseX, mouseY, mouseButton));
	}
}