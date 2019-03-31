package team.hollow.neutronia.client.guidebook.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringTextComponent;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.client.guidebook.BookEntry;
import team.hollow.neutronia.client.guidebook.EntryDisplayState;
import team.hollow.neutronia.init.NConstants;
import team.hollow.neutronia.notebook.Notebook;

import java.awt.*;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public abstract class GuiBook extends Screen {

	public static final int FULL_WIDTH = 272;
	public static final int FULL_HEIGHT = 180;
	public static final int PAGE_WIDTH = 116;
	public static final int PAGE_HEIGHT = 156;
	public static final int TOP_PADDING = 18;
	public static final int LEFT_PAGE_X = 15;
	public static final int RIGHT_PAGE_X = 141;
	public static final int TEXT_LINE_HEIGHT = 9;
	public static final int MAX_BOOKMARKS = 10;

	public final Notebook book;
	
	private static int lastSound;
	public int bookLeft, bookTop;

	private List<String> tooltip;
	private ItemStack tooltipStack;
	private Pair<BookEntry, Integer> targetPage;
	protected int page = 0, maxpages = 0;

	public int ticksInBook;
	public int maxScale;
	
	boolean needsBookmarkUpdate = false;

	public GuiBook(Notebook book) {
		super(new StringTextComponent(book.name));
		this.book = book;
	}

	public MinecraftClient getMinecraft() {
		return minecraft;
	}
	
	@Override
	public void init() {
		bookLeft = width / 2 - FULL_WIDTH / 2;
		bookTop = height / 2 - FULL_HEIGHT / 2;

		book.contents.currentGui = this;

		buttons.clear();

		//TODO: Add buttons back
		/*buttons.add(new GuiButtonBookBack(this, width / 2 - 9, bookTop + FULL_HEIGHT - 5));
		buttons.add(new GuiButtonBookArrow(this, bookLeft - 4, bookTop + FULL_HEIGHT - 6, true));
		buttons.add(new GuiButtonBookArrow(this, bookLeft + FULL_WIDTH - 14, bookTop + FULL_HEIGHT - 6, false));*/
		
//		addBookmarkButtons();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		MinecraftClient client = MinecraftClient.getInstance();
		super.render(mouseX, mouseY, partialTicks);

//		this.scaledMouseX = mouseX;
//		this.scaledMouseY = mouseY;

		GlStateManager.pushMatrix();

		int xTop = (client.window.getScaledWidth() / 2) - (NConstants.NOTEBOOK_WIDTH / 2);
		int yTop = (client.window.getScaledHeight() / 2) - (NConstants.NOTEBOOK_HEIGHT / 2);

		int left = xTop + 17;
		int right = xTop + 142;

		client.getTextureManager().bindTexture(NConstants.NOTEBOOK_TEXTURE);
		DrawableHelper.blit(xTop, yTop, 0, 0, NConstants.NOTEBOOK_WIDTH, NConstants.NOTEBOOK_HEIGHT, NConstants.NOTEBOOK_WIDTH, NConstants.NOTEBOOK_HEIGHT, NConstants.NOTEBOOK_WIDTH, NConstants.NOTEBOOK_TEX_HEIGHT);

		GlStateManager.popMatrix();
	}

	/*public void addBookmarkButtons() {
		buttonList.removeIf((b) -> b instanceof GuiButtonBookBookmark);
		int y = 0;
		List<Bookmark> bookmarks = PersistentData.data.getBookData(book).bookmarks;
		for(int i = 0; i < bookmarks.size(); i++) {
			Bookmark bookmark = bookmarks.get(i);
			buttonList.add(new GuiButtonBookBookmark(this, bookLeft + FULL_WIDTH, bookTop + TOP_PADDING + y, bookmark));
			y += 12;
		}
		
		y += (y == 0 ? 0 : 2);
		if(shouldAddAddBookmarkButton() && bookmarks.size() <= MAX_BOOKMARKS)
			buttonList.add(new GuiButtonBookBookmark(this, bookLeft + FULL_WIDTH, bookTop + TOP_PADDING + y, null));

		if(MultiblockVisualizationHandler.hasMultiblock && MultiblockVisualizationHandler.bookmark != null)
			buttonList.add(new GuiButtonBookBookmark(this, bookLeft + FULL_WIDTH, bookTop + TOP_PADDING + PAGE_HEIGHT - 20, MultiblockVisualizationHandler.bookmark, true));
	}*/
	
	protected boolean shouldAddAddBookmarkButton() {
		return false;
	}
	
	public void bookmarkThis() {
		// NO-OP
	}
	
	public void onFirstOpened() {
		// NO-OP
	}
	
	@Override
	public void tick() {
		if(!hasShiftDown())
			ticksInBook++;
		
		if(needsBookmarkUpdate) {
			needsBookmarkUpdate = false;
//			addBookmarkButtons();
		}
	}

	/*final void drawTooltip(int mouseX, int mouseY) {
		if(tooltipStack != null) {
			List<String> tooltip = this.getTooltipFromItem(tooltipStack);

			Pair<BookEntry, Integer> provider = book.contents.getEntryForStack(tooltipStack);
			if(provider != null && (!(this instanceof GuiBookEntry) || ((GuiBookEntry) this).entry != provider.getLeft())) {
				tooltip.add(TextFormat.GOLD + "(" + I18n.translate("patchouli.gui.lexicon.shift_for_recipe") + ')');
				targetPage = provider;
			}

			FontRenderer font = tooltipStack.getItem().getFontRenderer(tooltipStack);
			this.drawHoveringText(tooltip, mouseX, mouseY, (font == null ? fontRenderer : font));
			GuiUtils.postItemToolTip();
		} else if(tooltip != null && !tooltip.isEmpty()) {
			List<String> wrappedTooltip = new ArrayList<>();
			for (String s : tooltip)
				Collections.addAll(wrappedTooltip, s.split("\n"));
			GuiUtils.drawHoveringText(wrappedTooltip, mouseX, mouseY, width, height, -1, fontRenderer);
		}
	}*/

	final void resetTooltip() {
		tooltipStack = null;
		tooltip = null;
		targetPage = null;
	}

	public static void drawFromTexture(Notebook book, int x, int y, int u, int v, int w, int h) {
		MinecraftClient.getInstance().getTextureManager().bindTexture(book.bookResource);
		blit(x, y, u, v, w, h, 512, 256);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	/*@Override
	public void actionPerformed(ButtonWidget button) throws IOException {
		if(button instanceof GuiButtonBookBack)
			back(false);
		else if(button instanceof GuiButtonBookArrow)
			changePage(((GuiButtonBookArrow) button).left, false);
		else if(button instanceof GuiButtonBookBookmark) {
			GuiButtonBookBookmark bookmarkButton = (GuiButtonBookBookmark) button;
			Bookmark bookmark = bookmarkButton.bookmark;
			if(bookmark == null || bookmark.getEntry(book) == null)
				bookmarkThis();
			else {
				if(isShiftKeyDown() && !bookmarkButton.multiblock) {
					List<Bookmark> bookmarks = PersistentData.data.getBookData(book).bookmarks;
					bookmarks.remove(bookmark);
					PersistentData.save();
					needsBookmarkUpdate = true;
				} else displayLexiconGui(new GuiBookEntry(book, bookmark.getEntry(book), bookmark.page), true);
			}
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		switch(mouseButton) {
		case 0:
			if(targetPage != null && isShiftKeyDown()) {
				displayLexiconGui(new GuiBookEntry(book, targetPage.getLeft(), targetPage.getRight()), true);
				playBookFlipSound(book);
			}
			break;
		case 1: 
			back(true);
			break;
		case 3:  
			changePage(true, true);
			break;
		case 4:
			changePage(false, true);
			break;
		}
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		int w = Mouse.getEventDWheel();
		if(w < 0)
			changePage(false, true);
		else if(w > 0)
			changePage(true, true);
	}*/

	/*void back(boolean sfx) {
		if(!book.contents.guiStack.isEmpty()) {
			if(hasShiftDown()) {
				displayLexiconGui(new GuiBookLanding(book), false);
				book.contents.guiStack.clear();
			} else displayLexiconGui(book.contents.guiStack.pop(), false);
			
			if(sfx)
				playBookFlipSound(book);
		}
	}*/

	void changePage(boolean left, boolean sfx) {
		if(canSeePageButton(left)) {
			int oldpage = page;
			if(left)
				page--;
			else page++;

			onPageChanged();
			if(sfx)
				playBookFlipSound(book);
		}
	}

	void onPageChanged() {
		// NO-OP
	}
	
	public boolean canBeOpened() {
		return true;
	}

	public boolean canSeePageButton(boolean left) {
		return left ? page > 0 : (page + 1) < maxpages; 
	}

	public boolean canSeeBackButton() {
		return !book.contents.guiStack.isEmpty();
	}

	public void setTooltip(String... strings) {
		setTooltip(Arrays.asList(strings));
	}

	public void setTooltip(List<String> strings) {
		tooltip = strings;
	}

	public void setTooltipStack(ItemStack stack) {
		setTooltip();
		tooltipStack = stack;
	}

	public boolean isMouseInRelativeRange(int absMx, int absMy, int x, int y, int w, int h) {
		int mx = absMx - bookLeft;
		int my = absMy - bookTop;
		return mx > x && my > y && mx <= (x + w) && my <= (y + h);
	}

	private void drawGradient(int x, int y, int w, int h, int color) {
		int darkerColor = new Color(color).darker().getRGB();
		blit(x, y, w, h, color, darkerColor);
	}
	
	public void drawCenteredStringNoShadow(String s, int x, int y, int color) {
		font.draw(s, x - font.getStringWidth(s) / 2, y, color);
	}

	public List<AbstractButtonWidget> getButtonList() {
		return buttons;
	}

	public int getPage() {
		return page;
	}

	public static void drawSeparator(Notebook book, int x, int y) {
		int w = 110;
		int h = 3;
		int rx = x + PAGE_WIDTH / 2 - w / 2;

		GlStateManager.enableBlend();
		GlStateManager.color4f(1F, 1F, 1F, 0.8F);
		drawFromTexture(book, rx, y, 140, 180, w, h);
		GlStateManager.color4f(1F, 1F, 1F, 1F);
	}
	
	public static void drawLock(Notebook book, int x, int y) {
		drawFromTexture(book, x, y, 250, 180, 16, 16);
	}
	
	
	public static void drawMarking(Notebook book, int x, int y, int rand, EntryDisplayState state) {
		if(!state.hasIcon)
			return;
		
		GlStateManager.enableBlend();
		GlStateManager.disableAlphaTest();
		float alpha = state.hasAnimation ? ((float) Math.sin(/*ClientTicker.total*/10 * 0.2F) * 0.3F + 0.7F) : 1F;
		GlStateManager.color4f(1F, 1F, 1F, alpha);
		drawFromTexture(book, x, y, state.u, 197, 8, 8);
		GlStateManager.enableAlphaTest();
		GlStateManager.color3f(1F, 1F, 1F);
	}
	
	public static void drawPageFiller(Notebook book) {
		drawPageFiller(book, RIGHT_PAGE_X, TOP_PADDING);
	}
	
	public static void drawPageFiller(Notebook book, int x, int y) {
		GlStateManager.enableBlend();
		GlStateManager.color4f(1F, 1F, 1F, 1F);
		MinecraftClient.getInstance().getTextureManager().bindTexture(book.fillerResource);
		blit(x + PAGE_WIDTH / 2 - 64, y + PAGE_HEIGHT / 2 - 74, 0, 0, 128, 128, 128, 128);
	}

	public static void playBookFlipSound(Notebook book) {
		/*if(ClientTicker.ticksInGame - lastSound > 6) {
			SoundEvent sfx = PatchouliSounds.getSound(book.flipSound, PatchouliSounds.book_flip);
			Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(sfx, (float) (0.7 + Math.random() * 0.3)));
			lastSound = ClientTicker.ticksInGame;
		}*/
	}

	public static void openWebLink(String address) {
		try {
			Class<?> oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop").invoke(null);
			oclass.getMethod("browse", URI.class).invoke(object, new URI(address));
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/*public void displayLexiconGui(GuiBook gui, boolean push) {
		book.contents.openLexiconGui(gui, push);
	}*/
	
}
