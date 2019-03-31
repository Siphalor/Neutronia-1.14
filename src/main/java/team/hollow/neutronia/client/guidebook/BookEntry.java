package team.hollow.neutronia.client.guidebook;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.client.PersistentData;
import team.hollow.neutronia.client.PersistentData.DataHolder.BookData;
import team.hollow.neutronia.client.guidebook.page.PageEmpty;
import team.hollow.neutronia.notebook.Notebook;
import team.hollow.neutronia.utils.ItemStackUtil;
import team.hollow.neutronia.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookEntry extends AbstractReadStateHolder implements Comparable<BookEntry> {

	String name, category, flag;

	@SerializedName("icon")
	String iconRaw;

	boolean priority = false;
	boolean secret = false;
	@SerializedName("read_by_default")
	boolean readByDefault = false;
	BookPage[] pages;
	String advancement, turnin;
	int sortnum;

	transient Identifier resource;
	transient Notebook book, trueProvider;
	transient BookCategory lcategory = null;
	transient BookIcon icon = null;
	transient List<BookPage> realPages = new ArrayList<>();
	transient List<ItemStackUtil.StackWrapper> relevantStacks = new LinkedList<>();
	transient boolean locked;

	transient boolean built;

	public String getName() {
		return name;
	}

	public List<BookPage> getPages() {
		List<BookPage> pages = realPages;

		return pages.isEmpty() ? NO_PAGE : pages;
	}

	public int getPageFromAnchor(String anchor) {
		List<BookPage> pages = getPages();
		for (int i = 0; i < pages.size(); i++) {
			BookPage page = pages.get(i);
			if (page.anchor != null && anchor.equals(page.anchor))
				return i;
		}

		return -1;
	}

	private static final List<BookPage> NO_PAGE = ImmutableList.of(new PageEmpty());

	public boolean isPriority() {
		return priority;
	}

	public BookIcon getIcon() {
		if(icon == null)
			icon = new BookIcon(iconRaw); 

		return icon;
	}

	public BookCategory getCategory() {
		if(lcategory == null) {
			if(category.contains(":"))
				lcategory = book.contents.categories.get(new Identifier(category));
			else lcategory = book.contents.categories.get(new Identifier(book.getModNamespace(), category));
		}

		return lcategory;
	}

	public boolean isSecret() {
		return secret;
	}

	public boolean shouldHide() {
		return isSecret();
	}

	public Identifier getResource() {
		return resource;
	}

	public boolean isFoundByQuery(String query) {
		if(getName().toLowerCase().contains(query))
			return true;
		
		for(ItemStackUtil.StackWrapper wrapper : relevantStacks)
			if(StringUtils.stripControlCodes(wrapper.stack.getDisplayName().getFormattedText()).toLowerCase().contains(query))
				return true;
		
		return false;
	}

	@Override
	public int compareTo(BookEntry o) {
		if(o.locked != this.locked)
			return this.locked ? 1 : -1;
		
		EntryDisplayState ourState = getReadState();
		EntryDisplayState otherState = o.getReadState();
		
		if(ourState != otherState)
			return ourState.compareTo(otherState);

		if(o.priority != this.priority)
			return this.priority ? -1 : 1;

		int sort = this.sortnum - o.sortnum;

		return sort == 0 ? this.name.compareTo(o.name) : sort;
	}

	public void setBook(Notebook book) {
		this.book = book;
	}

	public void build(Identifier resource) {
		if(built)
			return;

		this.resource = resource;
		for(int i = 0; i < pages.length; i++) {
			realPages.add(pages[i]);
			try {
				pages[i].build(this, i);
			} catch(Exception e) {
				throw new RuntimeException("Error while loading entry " + resource + " page " + i, e);
			}
		}

		built = true;
	}

	public void addRelevantStack(ItemStack stack, int page) {
		ItemStackUtil.StackWrapper wrapper = ItemStackUtil.wrapStack(stack);
		relevantStacks.add(wrapper);

		if(!book.contents.recipeMappings.containsKey(wrapper))
			book.contents.recipeMappings.put(wrapper, Pair.of(this, page / 2));
	}

	public boolean isStackRelevant(ItemStack stack) {
		return relevantStacks.contains(ItemStackUtil.wrapStack(stack));
	}

	public Notebook getBook() {
		return book;
	}

	public Notebook getTrueProvider() {
		return trueProvider;
	}

	public boolean isExtension() {
		return getTrueProvider() != null && getTrueProvider() != getBook();
	}

	@Override
	protected EntryDisplayState computeReadState() {
		BookData data = PersistentData.data.getBookData(book);
		if(data != null && getResource() != null && !readByDefault && !data.viewedEntries.contains(getResource().toString()))
			return EntryDisplayState.UNREAD;

		if(turnin != null && !turnin.isEmpty())
			return EntryDisplayState.PENDING;

		/*for(BookPage page : pages)
			if(page instanceof PageQuest && ((PageQuest) page).isCompleted(book))
				return EntryDisplayState.COMPLETED;*/
		
		return EntryDisplayState.NEUTRAL;
	}

	@Override
	public void markReadStateDirty() {
		super.markReadStateDirty();
		getCategory().markReadStateDirty();
	}

}