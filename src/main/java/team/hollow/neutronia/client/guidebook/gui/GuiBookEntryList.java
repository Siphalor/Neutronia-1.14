package team.hollow.neutronia.client.guidebook.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import team.hollow.neutronia.client.guidebook.BookEntry;
import team.hollow.neutronia.notebook.Notebook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class GuiBookEntryList extends GuiBook {

	public static final int ENTRIES_PER_PAGE = 13;
	public static final int ENTRIES_IN_FIRST_PAGE = 11;
	
	BookTextRenderer text;
	
	List<ButtonWidget> dependentButtons;
	List<BookEntry> allEntries;
	List<BookEntry> visibleEntries;
	
	TextFieldWidget searchField;

	public GuiBookEntryList(Notebook book) {
		super(book);
	}
	
	@Override
	public void init() {
		super.init();
		
		text = new BookTextRenderer(this, getDescriptionText(), LEFT_PAGE_X, TOP_PADDING + 22);
		
		visibleEntries = new ArrayList<>();
		allEntries = new ArrayList<>(getEntries());
		allEntries.removeIf(BookEntry::shouldHide);
		if(shouldSortEntryList())
			Collections.sort(allEntries);
		
		searchField = new TextFieldWidget(font, 160, 170, 90, 12);
		searchField.setMaxLength(32);
		searchField.setIsEditable(false);
		searchField.setFocused(true);
		
		dependentButtons = new ArrayList<>();
		buildEntryButtons();
	}
	
	protected abstract String getName();
	protected abstract String getDescriptionText();
	protected abstract Collection<BookEntry> getEntries();
	
	protected boolean doesEntryCountForProgress(BookEntry entry) {
		return true;
	}
	
	protected boolean shouldDrawProgressBar() {
		return true;
	}
	
	protected boolean shouldSortEntryList() {
		return true;
	}
	
	protected void addSubcategoryButtons() {
		// NO-OP
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		super.render(mouseX, mouseY, partialTicks);
		
		if(page == 0) {
			drawCenteredStringNoShadow(getName(), LEFT_PAGE_X + PAGE_WIDTH / 2, TOP_PADDING, book.headerColor);
			drawCenteredStringNoShadow(I18n.translate("patchouli.gui.lexicon.chapters"), RIGHT_PAGE_X + PAGE_WIDTH / 2, TOP_PADDING, book.headerColor);

			drawSeparator(book, LEFT_PAGE_X, TOP_PADDING + 12);
			drawSeparator(book, RIGHT_PAGE_X, TOP_PADDING + 12);

			text.render(mouseX, mouseY);
		} else if(page % 2 == 1 && page == maxpages - 1 && dependentButtons.size() <= ENTRIES_PER_PAGE)
			drawPageFiller(book);
		
		/*if(!searchField.getText().isEmpty()) {
			GlStateManager.color(1F, 1F, 1F, 1F);
			drawFromTexture(book, searchField.x - 8, searchField.y, 140, 183, 99, 14);
			boolean unicode = fontRenderer.getUnicodeFlag();
			font.setRightToLeft(true);
			font.draw(searchField.getText(), searchField.x + 7, searchField.y + 1, 0);
			font.setRightToLeft(unicode);
		}*/
		
		if(visibleEntries.isEmpty()) {
			drawCenteredStringNoShadow(I18n.translate("patchouli.gui.lexicon.no_results"), GuiBook.RIGHT_PAGE_X + GuiBook.PAGE_WIDTH / 2, 80, 0x333333);
			GlStateManager.scalef(2F, 2F, 2F);
			drawCenteredStringNoShadow(I18n.translate("patchouli.gui.lexicon.sad"), GuiBook.RIGHT_PAGE_X / 2 + GuiBook.PAGE_WIDTH / 4, 47, 0x999999);
			GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		}
	}
	
	/*@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		text.click(mouseX, mouseY, mouseButton);
		searchField.mouseClicked(mouseX - bookLeft, mouseY - bookTop, mouseButton);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		
		if(keyCode == 28) { // Enter
			if(visibleEntries.size() == 1)
				displayLexiconGui(new GuiBookEntry(book, visibleEntries.get(0)), true);
		} else {
			String currQuery = searchField.getText();
			searchField.textboxKeyTyped(typedChar, keyCode);
			if(!searchField.getText().equals(currQuery))
				buildEntryButtons();
		}
	}
	
	@Override
	public void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		
		if(button instanceof GuiButtonCategory)
			displayLexiconGui(new GuiBookCategory(book, ((GuiButtonCategory) button).getCategory()), true);
		else if(button instanceof GuiButtonEntry)
			GuiBookEntry.displayOrBookmark(this, ((GuiButtonEntry) button).getEntry());
	}*/
	
	@Override
	void onPageChanged() {
		buildEntryButtons();
	}
	
	void buildEntryButtons() {
		buttons.removeAll(dependentButtons);
		dependentButtons.clear();
		visibleEntries.clear();
		
		String query = searchField.getText().toLowerCase();
		allEntries.stream().filter((e) -> e.isFoundByQuery(query)).forEach(visibleEntries::add);

		maxpages = 1;
		int count = visibleEntries.size();
		count -= ENTRIES_IN_FIRST_PAGE;
		if(count > 0)
			maxpages += (int) Math.ceil((float) count / (ENTRIES_PER_PAGE * 2));
		
		while(getEntryCountStart() > visibleEntries.size())
			page--;
		
		if(page == 0) {
			addEntryButtons(RIGHT_PAGE_X, TOP_PADDING + 20, 0, ENTRIES_IN_FIRST_PAGE);
			addSubcategoryButtons();
		} else {
			int start = getEntryCountStart();
			addEntryButtons(LEFT_PAGE_X, TOP_PADDING, start, ENTRIES_PER_PAGE);
			addEntryButtons(RIGHT_PAGE_X, TOP_PADDING, start + ENTRIES_PER_PAGE, ENTRIES_PER_PAGE);
		}
	}
	
	int getEntryCountStart() {
		if(page == 0)
			return 0;
		
		int start = ENTRIES_IN_FIRST_PAGE;
		start += (ENTRIES_PER_PAGE * 2) * (page - 1);
		return start;
	}
	
	void addEntryButtons(int x, int y, int start, int count) {
		for(int i = 0; i < count && (i + start) < visibleEntries.size(); i++) {
			/*ButtonWidget button = new GuiButtonEntry(this, bookLeft + x, bookTop + y + i * 11, visibleEntries.get(start + i), start + i);
			buttons.add(button);
			dependentButtons.add(button);*/
		}
	}

}