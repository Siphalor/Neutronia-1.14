package team.hollow.neutronia.notebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
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

public class NotebookRegistry implements SimpleSynchronousResourceReloadListener {

    public static final NotebookRegistry INSTANCE = new NotebookRegistry();
    public static final String BOOKS_LOCATION = "neutronia_guidebooks";
    public static final int PREFIX_LENGTH = "neutronia_guidebooks/".length();
    public static final int SUFFIX_LENGTH = ".json".length();

    public final Map<Identifier, Notebook> books = new HashMap<>();
    private final Map<Pair<ModContainer, Identifier>, String> foundBooks = new HashMap<>();
    public Gson gson;

    private NotebookRegistry() {
        gson = new GsonBuilder().create();
    }

    public void init() {
        foundBooks.forEach(((pair, s) -> {
            ModContainer mod = pair.getLeft();
            Identifier res = pair.getRight();

            InputStream stream = mod.getMetadata().getClass().getResourceAsStream(s);
            loadBook(mod, res, stream, false);
        }));

        NotebookFolderLoader.findBooks();
    }

    void loadBook(ModContainer mod, Identifier res, InputStream stream, boolean external) {
        Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        Notebook book = gson.fromJson(reader, Notebook.class);

        books.put(res, book);
        book.build(mod, res, external);
    }

    private void test(JsonObject jsonObject) {
        String registryName = JsonHelper.getString(jsonObject, "registry_name");
        String bookName = JsonHelper.getString(jsonObject, "name");
        Identifier name = new Identifier(registryName.split(":")[0], registryName.split(":")[1]);
        /*Reader reader = new BufferedReader(new InputStreamReader(FabricLoader.getInstance().getModContainer(name.getNamespace()).get().getMetadata().getClass().getResourceAsStream(jsonObject.getAsString()), StandardCharsets.UTF_8));
        Notebook book = gson.fromJson(reader, Notebook.class);
        books.put(name, book);
        book.build(FabricLoader.getInstance().getModContainer(registryName.split(":")[0]).get(), new Identifier(registryName), false);*/
        RegistryUtils.registerItem(new NotebookItem(new Item.Settings().stackSize(1).itemGroup(ItemGroup.MISC), bookName, books.values().iterator().next()), name);
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(Neutronia.MOD_ID, "book_registry");
    }

    @Override
    public void apply(ResourceManager resourceManager) {
        Gson gson_1 = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

        for (Identifier identifier_1 : resourceManager.findResources(BOOKS_LOCATION, "book_info.json"::equals)) {
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
    }

    @Environment(EnvType.CLIENT)
    public void reload() {
        books.values().forEach(Notebook::reloadContents);
    }

}