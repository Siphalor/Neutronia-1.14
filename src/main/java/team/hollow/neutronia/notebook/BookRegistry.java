package team.hollow.neutronia.notebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.Neutronia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookRegistry implements SimpleSynchronousResourceReloadListener {

	public static final BookRegistry INSTANCE = new BookRegistry();
	public static final String BOOKS_LOCATION = "guidebooks";

	public final Map<Identifier, Notebook> books = new HashMap<>();
	private final Map<Pair<ModContainer, Identifier>, String> foundBooks = new HashMap<>();
	public Gson gson;

	public BookRegistry() {
		gson = new GsonBuilder().create();
	}

	public void init() {
		foundBooks.forEach(((pair, s) -> {
			ModContainer mod = pair.getLeft();
			Identifier res = pair.getRight();

			InputStream stream = mod.getMetadata().getClass().getResourceAsStream(s);
			loadBook(mod, res, stream, false);
		}));

		BookFolderLoader.findBooks();
	}

	void loadBook(ModContainer mod, Identifier res, InputStream stream, boolean external) {
		Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
		Notebook book = gson.fromJson(reader, Notebook.class);

		books.put(res, book);
		book.build(mod, res, external);
	}

	@Override
	public Identifier getFabricId() {
		return new Identifier(Neutronia.MOD_ID, "book_registry");
	}

	@Override
	public void apply(ResourceManager resourceManager) {
		Collection<Identifier> resources = resourceManager.findResources("guidebooks", "book_info.json"::equals);
        resources.forEach(resourceIdentifier -> {
			try {
				Resource resource = resourceManager.getResource(resourceIdentifier);
				String json = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);

				/*String fileStr = resource.getId().toString().replaceAll("\\\\", "/");
				String relPath = fileStr.substring(fileStr.indexOf(BOOKS_LOCATION) + BOOKS_LOCATION.length() + 1);
				String bookName = relPath.substring(0, relPath.indexOf("/"));

				String assetPath = fileStr.substring(fileStr.indexOf("/data"));
				Identifier bookId = new Identifier(resourceIdentifier.getNamespace(), bookName);

                System.out.println(String.format("Found book_info.json at: %s", resourceIdentifier.toString()));

				foundBooks.put(Pair.of(FabricLoader.getInstance().getModContainer(resourceIdentifier.getNamespace()).isPresent() ?
						FabricLoader.getInstance().getModContainer(resourceIdentifier.getNamespace()).get() : null, resourceIdentifier), assetPath);*/
				Neutronia.getLogger().info(json);
				resource.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}