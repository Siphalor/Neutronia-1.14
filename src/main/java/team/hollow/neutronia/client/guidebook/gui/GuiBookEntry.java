package team.hollow.neutronia.client.guidebook.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.api.ComponentRenderContext;
import team.hollow.neutronia.client.PersistentData;
import team.hollow.neutronia.client.PersistentData.DataHolder.BookData;
import team.hollow.neutronia.client.PersistentData.DataHolder.BookData.Bookmark;
import team.hollow.neutronia.client.guidebook.BookEntry;
import team.hollow.neutronia.client.guidebook.BookPage;
import team.hollow.neutronia.notebook.Notebook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiBookEntry extends GuiBook implements ComponentRenderContext {

	BookEntry entry;
	BookPage leftPage, rightPage;
	
	Map<ButtonWidget, Runnable> customButtons = new HashMap();

	public GuiBookEntry(Notebook book, BookEntry entry) {
		this(book, entry, 0);
	}

	public GuiBookEntry(Notebook book, BookEntry entry, int page) {
		super(book);
		this.entry = entry;
		this.page = page; 
	}

	@Override
	public void init() {
		super.init();

		maxpages = (int) Math.ceil((float) entry.getPages().size() / 2);
		setupPages();
	}
	
	@Override
	public void onFirstOpened() {
		super.onFirstOpened();

		boolean dirty = false;
		String key = entry.getResource().toString();
		
		BookData data = PersistentData.data.getBookData(book);
		
		if(!data.viewedEntries.contains(key)) {
			data.viewedEntries.add(key);
			dirty = true;
			entry.markReadStateDirty();
		}
		
		int index = data.history.indexOf(key);
		if(index != 0) {
			if(index > 0)
				data.history.remove(key);
			
			data.history.add(0, key);
			while(data.history.size() > GuiBookEntryList.ENTRIES_PER_PAGE)
				data.history.remove(GuiBookEntryList.ENTRIES_PER_PAGE);
			
			dirty = true;
		}

		if(dirty)
			PersistentData.save();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		drawPage(leftPage, mouseX, mouseY, partialTicks);
		drawPage(rightPage, mouseX, mouseY, partialTicks);

		if(rightPage == null)
			drawPageFiller(leftPage.book);
	}

	/*@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		clickPage(leftPage, mouseX, mouseY, mouseButton);
		clickPage(rightPage, mouseX, mouseY, mouseButton);
	}

	@Override
	public void actionPerformed(ButtonWidget button) throws IOException {
		if(customButtons.containsKey(button)) {
			customButtons.get(button).run();
			return;
		}
		
		if((leftPage != null && leftPage.interceptButton(button)) || (rightPage != null && rightPage.interceptButton(button)))
			return;
		
		super.actionPerformed(button);
	}*/
	
	void drawPage(BookPage page, int mouseX, int mouseY, float pticks) {
		if(page == null)
			return;

		GlStateManager.pushMatrix();
		GlStateManager.translatef(page.left, page.top, 0);
		page.render(mouseX - page.left, mouseY - page.top, pticks);
		GlStateManager.popMatrix();
	}

	void clickPage(BookPage page, int mouseX, int mouseY, int mouseButton) {
		if(page != null)
			page.mouseClicked(mouseX - page.left, mouseY - page.top, mouseButton);
	}

	@Override
	void onPageChanged() {
		setupPages();
		needsBookmarkUpdate = true;
	}

	void setupPages() {
		customButtons.clear();
		
		if(leftPage != null)
			leftPage.onHidden(this);
		if(rightPage != null)
			rightPage.onHidden(this);
		
		List<BookPage> pages = entry.getPages();
		int leftNum = page * 2;
		int rightNum = (page * 2) + 1;

		leftPage  = leftNum  < pages.size() ? pages.get(leftNum)  : null;
		rightPage = rightNum < pages.size() ? pages.get(rightNum) : null;

		if(leftPage != null)
			leftPage.onDisplayed(this, LEFT_PAGE_X, TOP_PADDING);
		if(rightPage != null)
			rightPage.onDisplayed(this, RIGHT_PAGE_X, TOP_PADDING);
	}

	public BookEntry getEntry() {
		return entry;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || (obj instanceof GuiBookEntry && ((GuiBookEntry) obj).entry == entry && ((GuiBookEntry) obj).page == page);
	}

	@Override
	public boolean canBeOpened() {
		return !equals(MinecraftClient.getInstance().currentScreen);
	}

	@Override
	protected boolean shouldAddAddBookmarkButton() {
		return !isBookmarkedAlready();
	}
	
	boolean isBookmarkedAlready() {
		String entryKey = entry.getResource().toString();
		BookData data = PersistentData.data.getBookData(book);
		
		for(Bookmark bookmark : data.bookmarks)
			if(bookmark.entry.equals(entryKey) && bookmark.page == page)
				return true;
		
		return false;
	}

	@Override
	public void bookmarkThis() {
		String entryKey = entry.getResource().toString();
		BookData data = PersistentData.data.getBookData(book);
		data.bookmarks.add(new Bookmark(entryKey, page));
		PersistentData.save();
		needsBookmarkUpdate = true;
	}
	
	public static void displayOrBookmark(GuiBook currGui, BookEntry entry) {
		Notebook book = currGui.book;
		GuiBookEntry gui = new GuiBookEntry(currGui.book, entry);
		
		if(Screen.hasShiftDown()) {
			BookData data = PersistentData.data.getBookData(book);

			if(gui.isBookmarkedAlready()) {
				String key = entry.getResource().toString();
				data.bookmarks.removeIf((bm) -> bm.entry.equals(key) && bm.page == 0);
				PersistentData.save();
				currGui.needsBookmarkUpdate = true;
				return;
			} else if(data.bookmarks.size() < MAX_BOOKMARKS) {
				gui.bookmarkThis();
				currGui.needsBookmarkUpdate = true;
				return;
			}
		}
		
		book.contents.openLexiconGui(gui, true);
	}

	@Override
	public Screen getGui() {
		return this;
	}

	@Override
	public TextRenderer getFont() {
		return font;
	}
	
	@Override
	public void renderItemStack(int x, int y, int mouseX, int mouseY, ItemStack stack) {
		if(stack == null || stack.isEmpty())
			return;

		GuiLighting.enableForItems();
		getMinecraft().getItemRenderer().renderGuiItem(stack, x, y);
		getMinecraft().getItemRenderer().renderGuiItemOverlay(font, stack, x, y);
		
		if(isMouseInRelativeRange(mouseX, mouseY, x, y, 16, 16))
			setTooltipStack(stack);
	}
	
	@Override
	public void renderIngredient(int x, int y, int mouseX, int mouseY, Ingredient ingr) {
		ItemStack[] stacks = ingr.getStackArray();
		if(stacks.length > 0)
			renderItemStack(x, y, mouseX, mouseY, stacks[(ticksInBook / 20) % stacks.length]);
	}
	
	@Override
	public void setHoverTooltip(List<String> tooltip) {
		setTooltip(tooltip);
	}

	@Override
	public boolean isAreaHovered(int mouseX, int mouseY, int x, int y, int w, int h) {
		return isMouseInRelativeRange(mouseX, mouseY, x, y, w, h);
	}
	
	@Override
	public void registerButton(ButtonWidget button, int pageNum, Runnable onClick) {
		button.x += bookLeft + ((pageNum % 2) == 0 ? LEFT_PAGE_X : RIGHT_PAGE_X);
		button.y += bookTop;
		
		customButtons.put(button, onClick);
		buttons.add(button);
	}

	@Override
	public Identifier getBookTexture() {
		return book.bookResource;
	}

	@Override
	public Identifier getCraftingTexture() {
		return book.craftingResource;
	}

	@Override
	public int getTextColor() {
		return book.textColor;
	}

	@Override
	public int getHeaderColor() {
		return book.headerColor;
	}

}