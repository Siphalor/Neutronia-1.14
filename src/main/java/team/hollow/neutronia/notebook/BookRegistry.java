package team.hollow.neutronia.notebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.Neutronia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookRegistry {

	public static final BookRegistry INSTANCE = new BookRegistry();
	public static final String BOOKS_LOCATION = Neutronia.MOD_ID + "_books";

	public final Map<Identifier, Notebook> books = new HashMap<>();
	public Gson gson;

	private BookRegistry() { 
		gson = new GsonBuilder().create();
	}

	public void init() {
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();
		Map<Pair<ModContainer, Identifier>, String> foundBooks = new HashMap<>();

		mods.forEach((mod) -> {
			String id = mod.getMetadata().getId();
			JsonUtils.findFiles(mod, String.format("data/%s/%s", id, BOOKS_LOCATION), (path) -> Files.exists(path),
					(path, file) -> {
						if(file.toString().endsWith("book_info.json")) {
							String fileStr = file.toString().replaceAll("\\\\", "/");
							String relPath = fileStr.substring(fileStr.indexOf(BOOKS_LOCATION) + BOOKS_LOCATION.length() + 1);
							String bookName = relPath.substring(0, relPath.indexOf("/"));

							if(bookName.contains("/")) {
								(new IllegalArgumentException("Ignored book_info.json @ " + file)).printStackTrace();
								return true;
							}

							String assetPath = fileStr.substring(fileStr.indexOf("/data"));
							Identifier bookId = new Identifier(id, bookName);
							foundBooks.put(Pair.of(mod, bookId), assetPath);
						}

						return true;
					}, false, true);
		});

		foundBooks.forEach((pair, file) -> {
			ModContainer mod = pair.getLeft();
			Identifier res = pair.getRight();

			InputStream stream = mod.getMetadata().getClass().getResourceAsStream(file);
			loadBook(mod, res, stream, false);
		});
		
		BookFolderLoader.findBooks();
	}
	
	public void loadBook(ModContainer mod, Identifier res, InputStream stream, boolean external) {
		Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
		Notebook book = gson.fromJson(reader, Notebook.class);

		books.put(res, book);
		book.build(mod, res, external);
	}

	@Environment(EnvType.CLIENT)
	public void reload() {
		books.values().forEach(Notebook::reloadContents);
		books.values().forEach(Notebook::reloadExtensionContents);
	}

}