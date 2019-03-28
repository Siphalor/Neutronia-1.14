package team.hollow.neutronia.client.book;

import com.google.gson.annotations.SerializedName;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.book.Book;
import vazkii.patchouli.common.base.PatchouliConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BookCategory extends AbstractReadStateHolder implements Comparable<BookCategory> {

	String name, description, parent, flag;
	@SerializedName("icon")
	String iconRaw;
	int sortnum;
	boolean secret = false;

	transient Book book, trueProvider;
	transient boolean checkedParent = false;
	transient BookCategory parentCategory;
	transient List<BookCategory> children = new ArrayList<>();
	transient List<BookEntry> entries = new ArrayList<>();
	transient BookIcon icon = null;
	transient Identifier resource;
	
	transient boolean built;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BookIcon getIcon() {
		if(icon == null)
			icon = new BookIcon(iconRaw);

		return icon;
	}

	public void addEntry(BookEntry entry) {
		this.entries.add(entry);
	}

	public void addChildCategory(BookCategory category) {
		children.add(category);
	}

	public List<BookEntry> getEntries() {
		return entries;
	}

	public BookCategory getParentCategory() {
		if(!checkedParent && !isRootCategory()) {
			if(parent.contains(":"))
				parentCategory = book.contents.categories.get(new Identifier(parent));
			else parentCategory = book.contents.categories.get(new Identifier(book.getModNamespace(), parent));

			checkedParent = true;
		}

		return parentCategory;
	}

	public boolean isSecret() {
		return secret;
	}

	public boolean shouldHide() {
		return isSecret();
	}

	public boolean isRootCategory() {
		return parent == null || parent.isEmpty();
	}

	public Identifier getResource() {
		return resource;
	}

	public boolean canAdd() {
		return flag == null || flag.isEmpty() || PatchouliConfig.getConfigFlag(flag);
	}

	@Override
	public int compareTo(BookCategory o) {
		return this.sortnum - o.sortnum;
	}

	public void setBook(Book book) {
		if(book.isExtension) {
			this.book = book.extensionTarget;
			trueProvider = book;
		} else this.book = book;	
	}

	public void build(Identifier resource) {
		if(built)
			return;
		
		this.resource = resource;
		BookCategory parent = getParentCategory();
		if(parent != null)
			parent.addChildCategory(this);
		
		built = true;
	}

	public Book getBook() {
		return book;
	}
	
	public Book getTrueProvider() {
		return trueProvider;
	}

	public boolean isExtension() {
		return getTrueProvider() != getBook();
	}
	
	@Override
	protected EntryDisplayState computeReadState() {
		Stream<EntryDisplayState> entryStream = entries.stream().map(BookEntry::getReadState);
		Stream<EntryDisplayState> childrenStream = children.stream().map(BookCategory::getReadState);
		return mostImportantState(entryStream, childrenStream);
	}
	
	@Override
	public void markReadStateDirty() {
		super.markReadStateDirty();
		
		if(parentCategory != null)
			parentCategory.markReadStateDirty();
		else book.contents.markReadStateDirty();
	}

}
