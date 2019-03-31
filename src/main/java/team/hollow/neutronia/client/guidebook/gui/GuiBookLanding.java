package team.hollow.neutronia.client.guidebook.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TextFormat;
import team.hollow.neutronia.client.guidebook.BookCategory;
import team.hollow.neutronia.notebook.Notebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GuiBookLanding extends GuiBook {

	BookTextRenderer text;
	int loadedCategories = 0;

	public GuiBookLanding(Notebook book) {
		super(book);
	}

	@Override
	public void init() {
		super.init();

		text = new BookTextRenderer(this, I18n.translate(book.landingText), LEFT_PAGE_X, TOP_PADDING + 25);

		boolean disableBar = true;

		int x = bookLeft + 25;
		int y = bookTop + FULL_HEIGHT - 25;
		int dist = 15;
		int pos = 0;
		
		/*// Resize
		if (maxScale > 2)
			buttons.add(new GuiButtonBookResize(this, x + (pos++) * dist, y, true));
		
		// History
		buttons.add(new GuiButtonBookHistory(this, x + (pos++) * dist, y));

		if(MinecraftClient.getInstance().player.isCreative())
			buttons.add(new GuiButtonBookEdit(this, x + (pos++) * dist, y));*/
		
		int i = 0;
		List<BookCategory> categories = new ArrayList<>(book.contents.categories.values());
		Collections.sort(categories);

		for(BookCategory category : categories) {	
			if(category.getParentCategory() != null || category.shouldHide())
				continue;

			addCategoryButton(i, category);
			i++;
		}
		addCategoryButton(i, null);
		loadedCategories = i + 1;
	}

	void addCategoryButton(int i, BookCategory category) {
		int x = RIGHT_PAGE_X + 10 + (i % 4) * 24;
		int y = TOP_PADDING + 25 + (i /4) * 24;

		/*if(category == null)
			buttons.add(new GuiButtonIndex(this, x, y));
		else buttons.add(new GuiButtonCategory(this, x, y, category));*/
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		text.render(mouseX, mouseY);

		drawCenteredStringNoShadow(I18n.translate("patchouli.gui.lexicon.categories"), RIGHT_PAGE_X + PAGE_WIDTH / 2, TOP_PADDING, book.headerColor);

		int topSeparator = TOP_PADDING + 12;
		int bottomSeparator = topSeparator + 25 + 24 * ((loadedCategories - 1) / 4 + 1);

		drawHeader();
		drawSeparator(book, RIGHT_PAGE_X, topSeparator);
		
		if(loadedCategories <= 16)
			drawSeparator(book, RIGHT_PAGE_X, bottomSeparator);

		if(book.contents.isErrored()) {
			int x = RIGHT_PAGE_X  + PAGE_WIDTH / 2; 
			int y = bottomSeparator + 12;
			
			drawCenteredStringNoShadow(I18n.translate("patchouli.gui.lexicon.loading_error"), x, y, 0xFF0000);
			drawCenteredStringNoShadow(I18n.translate("patchouli.gui.lexicon.loading_error_hover"), x, y + 10, 0x777777);

			x -= PAGE_WIDTH / 2;
			y -= 4;
			
			if(isMouseInRelativeRange(mouseX, mouseY, x, y, PAGE_WIDTH, 20))
				makeErrorTooltip();
		}
	}

	void drawHeader() {
		GlStateManager.color4f(1F, 1F, 1F, 1F);
		drawFromTexture(book, -8, 12, 0, 180, 140, 31);

		int color = book.nameplateColor;
		boolean unicode = font.isRightToLeft();
		font.draw(book.name, 13, 16, color);

		font.setRightToLeft(true);
		font.draw(book.contents.getSubtitle(), 24, 24, color);
		font.setRightToLeft(unicode);
	}
	
	void makeErrorTooltip() {
		Throwable e = book.contents.getException();
		List<String> lines = new LinkedList<>();
		while(e != null) {
			String msg = e.getMessage();
			if(msg != null && !msg.isEmpty())
				lines.add(e.getMessage());
			e = e.getCause();
		}
		
		if(!lines.isEmpty()) {
			lines.add(TextFormat.GREEN + I18n.translate("patchouli.gui.lexicon.loading_error_log"));
			setTooltip(lines);
		}
	}

	/*@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		text.click(mouseX, mouseY, mouseButton);
	}

	@Override
	public void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);

		if(button instanceof GuiButtonIndex)
			displayLexiconGui(new GuiBookIndex(book), true);

		else if(button instanceof GuiButtonCategory)
			displayLexiconGui(new GuiBookCategory(book, ((GuiButtonCategory) button).getCategory()), true);

		else if(button instanceof GuiButtonBookHistory)
			displayLexiconGui(new GuiBookHistory(book), true);

		else if(button instanceof GuiButtonBookConfig) {
			IModGuiFactory guiFactory = FMLClientHandler.instance().getGuiFactoryFor(book.owner);
			GuiScreen configGui = guiFactory.createConfigGui(this);
			mc.displayGuiScreen(configGui);
		}

		else if(button instanceof GuiButtonBookAdvancements)
			mc.displayGuiScreen(new GuiAdvancementsExt(mc.player.connection.getAdvancementManager(), this, book.advancementsTab));

		else if(button instanceof GuiButtonBookEdit) {
			if(isShiftKeyDown()) {
				long time = System.currentTimeMillis();
				book.reloadContentsAndExtensions();
				book.reloadLocks(false);
				displayLexiconGui(new GuiBookLanding(book), false);
				mc.player.sendMessage(new TextComponentTranslation("patchouli.gui.lexicon.reloaded", (System.currentTimeMillis() - time)));
			} else displayLexiconGui(new GuiBookWriter(book), true);
		}

		else if(button instanceof GuiButtonBookResize) {
			if(PersistentData.data.bookGuiScale >= maxScale)
				PersistentData.data.bookGuiScale = 0;
			else PersistentData.data.bookGuiScale = Math.max(2, PersistentData.data.bookGuiScale + 1);

			PersistentData.save();
			displayLexiconGui(this, false);
		}
	}*/

}