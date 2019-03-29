package team.hollow.neutronia.notebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.items.NotebookItem;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BookRegistry implements SimpleSynchronousResourceReloadListener {

    public static final BookRegistry INSTANCE = new BookRegistry();
    static final String BOOKS_LOCATION = "guidebooks";
    public static final int PREFIX_LENGTH = "guidebooks/".length();
    public static final int SUFFIX_LENGTH = ".json".length();

    public final Map<Identifier, Notebook> books = new HashMap<>();
    private final Map<Pair<ModContainer, Identifier>, String> foundBooks = new HashMap<>();
    public Gson gson;

    private BookRegistry() {
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

    private void test(JsonObject jsonObject) {
        String registryName = JsonHelper.getString(jsonObject, "registry_name");
        Identifier identifier = new Identifier(JsonHelper.getString(jsonObject, "creative_tab"));
        ItemGroup itemGroup = new ItemGroup(ItemGroup.GROUPS.length - 1, String.format("%s.%s", identifier.getNamespace(), identifier.getPath())) {
            @Override
            public ItemStack createIcon() {
                return stackSupplier.get();
            }

            @Override
            public void appendItems(DefaultedList<ItemStack> stacks) {
                if(stacksForDisplay != null){
                    stacksForDisplay.accept(stacks);
                    return;
                }
                super.appendItems(stacks);
            }
        };
        RegistryUtils.registerItem(new NotebookItem(new Item.Settings().stackSize(1).itemGroup(ItemGroup.MISC)), registryName);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(Neutronia.MOD_ID, "book_registry");
    }

    @Override
    public void apply(ResourceManager resourceManager) {
        Gson gson_1 = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

        for (Identifier identifier_1 : resourceManager.findResources(BOOKS_LOCATION, (string_1x) -> string_1x.endsWith(".json"))) {
            String string_1 = identifier_1.getPath();
            Identifier identifier_2 = new Identifier(identifier_1.getNamespace(), string_1.substring(PREFIX_LENGTH, string_1.length() - SUFFIX_LENGTH));

            try {
                Resource resource_1 = resourceManager.getResource(identifier_1);
                Throwable var8 = null;

                try {
                    JsonObject jsonObject_1 = JsonHelper.deserialize(gson_1, IOUtils.toString(resource_1.getInputStream(), StandardCharsets.UTF_8), JsonObject.class);
                    if (jsonObject_1 == null) {
                        Neutronia.getLogger().error("Couldn't load book information in {} as it's null or empty", identifier_2);
                    } else {
                        test(jsonObject_1);
                    }
                } catch (Throwable var19) {
                    var8 = var19;
                    throw var19;
                } finally {
                    if (resource_1 != null) {
                        if (var8 != null) {
                            try {
                                resource_1.close();
                            } catch (Throwable var18) {
                                var8.addSuppressed(var18);
                            }
                        } else {
                            resource_1.close();
                        }
                    }

                }
            } catch (IllegalArgumentException | JsonParseException var21) {
                Neutronia.getLogger().error("Parsing error loading book information {}", identifier_2, var21);
            } catch (IOException var22) {
                Neutronia.getLogger().error("Couldn't read custom advancement {} from {}", identifier_2, identifier_1, var22);
            }
        }

        /*Collection<Identifier> resources = resourceManager.findResources(BOOKS_LOCATION, "book_info.json"::equals);
        resources.forEach(resourceIdentifier -> {
            try {
                Resource resource = resourceManager.getResource(resourceIdentifier);
                String json = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);

//				String fileStr = resourceIdentifier.toString().replaceAll("\\\\", "/");
//				String relPath = fileStr.substring(fileStr.indexOf(BOOKS_LOCATION) + BOOKS_LOCATION.length() + 1);
//				String bookName = relPath.substring(0, relPath.indexOf("/"));
//
//				System.out.println(bookName);

				*//*String assetPath = fileStr.substring(fileStr.indexOf("/data"));
				Identifier bookId = new Identifier(resourceIdentifier.getNamespace(), bookName);*//*

                System.out.println(String.format("Found book_info.json at: %s", resourceIdentifier.toString()));

                foundBooks.put(Pair.of(FabricLoader.getInstance().getModContainer(resourceIdentifier.getNamespace()).isPresent() ?
                        FabricLoader.getInstance().getModContainer(resourceIdentifier.getNamespace()).get() : null, resourceIdentifier), "test");
                Neutronia.getLogger().info(json);
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
    }

}