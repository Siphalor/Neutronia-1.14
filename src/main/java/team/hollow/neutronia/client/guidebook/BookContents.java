package team.hollow.neutronia.client.guidebook;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.client.guidebook.gui.GuiBook;
import team.hollow.neutronia.client.guidebook.gui.GuiBookLanding;
import team.hollow.neutronia.notebook.Notebook;
import team.hollow.neutronia.notebook.NotebookRegistry;
import team.hollow.neutronia.utils.ItemStackUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class BookContents extends AbstractReadStateHolder {

	private static final String[] ORDINAL_SUFFIXES = new String[]{ "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	protected static final String DEFAULT_LANG = "en_us";
	
//	public static final HashMap<Identifier, Supplier<NotebookTemplate>> addonTemplates = new HashMap<>();

	public final Notebook book;

	public Map<Identifier, BookCategory> categories = new HashMap<>();
	public Map<Identifier, BookEntry> entries = new HashMap<>();
//	public Map<Identifier, Supplier<NotebookTemplate>> templates = new HashMap<>();
	public Map<ItemStackUtil.StackWrapper, Pair<BookEntry, Integer>> recipeMappings = new HashMap<>();
	private boolean errored = false;
	private Exception exception = null;

	public Stack<GuiBook> guiStack = new Stack<>();
	public GuiBook currentGui;
	
	public BookIcon indexIcon;

	public BookContents(Notebook book) {
		this.book = book;
	}

	public boolean isErrored() {
		return errored;
	}
	
	public Exception getException() {
		return exception;
	}

	public Pair<BookEntry, Integer> getEntryForStack(ItemStack stack) {
		return recipeMappings.get(ItemStackUtil.wrapStack(stack));
	}

	public GuiBook getCurrentGui() {
		if(currentGui == null)
			currentGui = new GuiBookLanding(book);

		return currentGui;
	}

	public void openLexiconGui(GuiBook gui, boolean push) {
		if(gui.canBeOpened()) {
			MinecraftClient mc = MinecraftClient.getInstance();
			if(push && mc.currentScreen instanceof GuiBook && gui != mc.currentScreen)
				guiStack.push((GuiBook) mc.currentScreen);

			mc.openScreen(gui);
			gui.onFirstOpened();
		}
	}

	public String getSubtitle() {
		String editionStr;

		try {
			int ver = Integer.parseInt(book.version);
			if(ver == 0)
				return I18n.translate(book.subtitle);

			editionStr = numberToOrdinal(ver); 
		} catch(NumberFormatException e) {
			editionStr = I18n.translate("patchouli.gui.lexicon.dev_edition");
		}

		return I18n.translate("patchouli.gui.lexicon.edition_str", editionStr);
	}

	public void reload(boolean isOverride) {
		errored = false;

		if(!isOverride) {
			currentGui = null;
			guiStack.clear();
			categories.clear();
			entries.clear();
//			templates.clear();
			recipeMappings.clear();
			
//			templates.putAll(addonTemplates);
			
			if(book.indexIconRaw == null || book.indexIconRaw.isEmpty())
				indexIcon = new BookIcon(book.bookTexture);
			else indexIcon = new BookIcon(book.indexIconRaw);
		}

		List<Identifier> foundCategories = new ArrayList<>();
		List<Identifier> foundEntries = new ArrayList<>();
		List<Identifier> foundTemplates = new ArrayList<>();
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();

		try { 
			String bookName = book.resourceLoc.getPath();

			findFiles("categories", foundCategories);
			findFiles("entries", foundEntries);
//			findFiles("templates", foundTemplates);

			foundCategories.forEach(c -> loadCategory(c, new Identifier(c.getNamespace(),
					String.format("%s/%s/%s/categories/%s.json", NotebookRegistry.BOOKS_LOCATION, bookName, DEFAULT_LANG, c.getPath())), book));
			foundEntries.forEach(e -> loadEntry(e, new Identifier(e.getNamespace(),
					String.format("%s/%s/%s/entries/%s.json", NotebookRegistry.BOOKS_LOCATION, bookName, DEFAULT_LANG, e.getPath())), book));
			/*foundTemplates.forEach(e -> loadTemplate(e, new Identifier(e.getNamespace(),
					String.format("%s/%s/%s/templates/%s.json", NotebookRegistry.BOOKS_LOCATION, bookName, DEFAULT_LANG, e.getPath())), book));*/

			entries.forEach((res, entry) -> {
				try {
					entry.build(res);
				} catch(Exception e) {
					throw new RuntimeException("Error while loading entry " + res, e);
				}
			});

			categories.forEach((res, category) -> {
				try {
					category.build(res);
				} catch(Exception e) {
					throw new RuntimeException("Error while loading category " + res, e);
				}
			});
		} catch (Exception e) {
			exception = e;
			errored = true;
			e.printStackTrace();
		}
	}

	protected void findFiles(String dir, List<Identifier> list) {
		ModContainer mod = book.owner;
		String id = mod.getMetadata().getId();
//		CraftingHelper.findFiles(mod, String.format("assets/%s/%s/%s/%s/%s", id, NotebookRegistry.BOOKS_LOCATION, book.resourceLoc.getPath(), DEFAULT_LANG, dir), null, pred(id, list), false, false);
		MinecraftClient.getInstance().getResourceManager().findResources(String.format("data/%s/%s/%s/%s/%s", id, NotebookRegistry.BOOKS_LOCATION, book.resourceLoc.getPath(), DEFAULT_LANG, dir), (string_1x) -> string_1x.endsWith(".json"));
	}
	
	private BiFunction<Path, Path, Boolean> pred(String modId, List<Identifier> list) {
		return (root, file) -> {
			Path rel = root.relativize(file);
			String relName = rel.toString();
			if(relName.endsWith(".json")) {
				relName = FilenameUtils.removeExtension(FilenameUtils.separatorsToUnix(relName));
				Identifier res = new Identifier(modId, relName);
				list.add(res);
			}

			return true;
		};
	}

	private void loadCategory(Identifier key, Identifier res, Notebook book) {
		try (Reader stream = loadLocalizedJson(res)) {
			BookCategory category = ClientBookRegistry.INSTANCE.gson.fromJson(stream, BookCategory.class);
			if (category == null)
				throw new IllegalArgumentException(res + " does not exist.");

			category.setBook(book);
			categories.put(key, category);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void loadEntry(Identifier key, Identifier res, Notebook book) {
		try (Reader stream = loadLocalizedJson(res)) {
			BookEntry entry = ClientBookRegistry.INSTANCE.gson.fromJson(stream, BookEntry.class);
			if (entry == null)
				throw new IllegalArgumentException(res + " does not exist.");

			entry.setBook(book);
			BookCategory category = entry.getCategory();
			if (category != null)
				category.addEntry(entry);
			else
				new RuntimeException("Entry " + key + " does not have a valid category.").printStackTrace();

			entries.put(key, entry);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/*private void loadTemplate(Identifier key, Identifier res, Notebook book) {
		Supplier<NotebookTemplate> supplier = () -> {
			try (Reader stream = loadLocalizedJson(res)) {
				return ClientNotebookRegistry.INSTANCE.gson.fromJson(stream, NotebookTemplate.class);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		};
		
		// test supplier
		NotebookTemplate template = supplier.get();
		if(template == null)
			throw new IllegalArgumentException(res + " could not be instantiated by the supplier.");
		
		templates.put(key, supplier);
	}*/

	private Reader loadLocalizedJson(Identifier res) {
		Identifier localized = new Identifier(res.getNamespace(),
				res.getPath().replaceAll(DEFAULT_LANG, ClientBookRegistry.INSTANCE.currentLang));

		InputStream input = loadJson(localized, res);
		if (input == null)
			throw new IllegalArgumentException(res + " does not exist.");

		return new InputStreamReader(new BufferedInputStream(input), StandardCharsets.UTF_8);
	}

	protected InputStream loadJson(Identifier resloc, Identifier fallback) {
		try {
			return MinecraftClient.getInstance().getResourceManager().getResource(resloc).getInputStream();
		} catch (IOException e) {
			//no-op
		}

		if(fallback != null) {
			System.err.println("Neutronia failed to load " + resloc + ". Switching to fallback.");
			return loadJson(fallback, null);
		}

		return null;
	}

	private static String numberToOrdinal(int i) {
		return i % 100 == 11 || i % 100 == 12 || i % 100 == 13 ? i + "th" : i + ORDINAL_SUFFIXES[i % 10];
	}

	@Override
	protected EntryDisplayState computeReadState() {
		Stream<EntryDisplayState> stream = categories.values().stream().filter(BookCategory::isRootCategory).map(BookCategory::getReadState);
		return mostImportantState(stream);
	}

}