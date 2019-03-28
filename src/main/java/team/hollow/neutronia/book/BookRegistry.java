package team.hollow.neutronia.book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.loot.LootSupplier;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.Neutronia;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookRegistry implements SimpleSynchronousResourceReloadListener {

	public static final BookRegistry INSTANCE = new BookRegistry();
	public static final String BOOKS_LOCATION = "guidebooks";

	public final Map<Identifier, Book> books = new HashMap<>();
	public Gson gson;

	private BookRegistry() {
		gson = new GsonBuilder().create();
	}

	public void loadBook(ModContainer mod, Identifier res, InputStream stream, boolean external) {
		Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
		String json = gson.toJson(reader);
		System.out.println(json);
		Book book = gson.fromJson(reader, Book.class);

		books.put(res, book);
		book.build(mod, res, external);
	}

	@Environment(EnvType.CLIENT)
	public void reload() {
		books.values().forEach(Book::reloadContents);
		books.values().forEach(Book::reloadExtensionContents);
	}

	@Override
	public Identifier getFabricId() {
		return new Identifier(Neutronia.MOD_ID, "book_registry");
	}

	@Override
	public void apply(ResourceManager resourceManager) {
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();
		Map<Pair<ModContainer, Identifier>, String> foundBooks = new HashMap<>();

		final int lootTablesLength = "guidebooks/".length();
		final int jsonLength = ".json".length();

		mods.forEach((mod) -> {
			String id = mod.getMetadata().getId();
			for (Identifier identifier_1 : resourceManager.findResources("guidebooks", (string_1x) -> string_1x.endsWith(".json"))) {
				System.out.println(identifier_1);
				String string_1 = identifier_1.getPath();
				File tagFile = new File(getTagLocation(identifier_1), string_1 + ".json");
				Identifier identifier_2 = new Identifier(identifier_1.getNamespace(), string_1.substring(lootTablesLength, string_1.length() - jsonLength));

				try {
					Resource resource_1 = resourceManager.getResource(identifier_1);
					Throwable var7 = null;

					try {
						LootSupplier lootSupplier_1 = JsonHelper.deserialize(gson, IOUtils.toString(resource_1.getInputStream(), StandardCharsets.UTF_8), LootSupplier.class);
						if (lootSupplier_1 != null) {
							String fileStr = string_1.replaceAll("\\\\", "/");
							String relPath = fileStr.substring(fileStr.indexOf(BOOKS_LOCATION) + BOOKS_LOCATION.length() + 1);
							String bookName = relPath.substring(0, relPath.indexOf("/"));

							if (bookName.contains("/")) {
								(new IllegalArgumentException("Ignored book.json @ " + string_1)).printStackTrace();
							}
							Identifier bookId = new Identifier(id, bookName);
							foundBooks.put(Pair.of(mod, bookId), bookName);
						}
					} catch (Throwable var17) {
						var7 = var17;
						throw var17;
					} finally {
						if (resource_1 != null) {
							if (var7 != null) {
								try {
									resource_1.close();
								} catch (Throwable var16) {
									var7.addSuppressed(var16);
								}
							} else {
								resource_1.close();
							}
						}

					}
				} catch (Throwable var19) {
					Neutronia.getLogger().error("Couldn't read loot table {} from {}", identifier_2, identifier_1, var19);
				}
			}
		});

		foundBooks.forEach((pair, file) -> {
			ModContainer mod = pair.getLeft();
			Identifier res = pair.getRight();

			InputStream stream = mod.getMetadata().getClass().getResourceAsStream(file);
			loadBook(mod, res, stream, false);
		});

		BookFolderLoader.findBooks();
	}

	public static File getTagLocation(Identifier tagId) {
		return new File(Neutronia.DATA_PACK_LOCATION, "data/" + tagId.getNamespace() + "/guidebooks");
	}

}
