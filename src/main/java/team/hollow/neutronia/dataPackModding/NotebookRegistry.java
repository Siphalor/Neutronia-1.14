package team.hollow.neutronia.dataPackModding;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.apache.commons.io.IOUtils;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.utils.registry.RegistryUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NotebookRegistry implements SimpleSynchronousResourceReloadListener {

    public static final NotebookRegistry INSTANCE = new NotebookRegistry();
    public static final String BOOKS_LOCATION = "neutronia_modding";
    public static final int PREFIX_LENGTH = "neutronia_modding/".length();
    public static final int SUFFIX_LENGTH = ".json".length();

    public Gson gson;

    private NotebookRegistry() {
        gson = new GsonBuilder().create();
    }

    void test(JsonObject jsonObject) {
        String type = JsonHelper.getString(jsonObject, "type");
        String registryName = JsonHelper.getString(jsonObject, "registry_name");
        Identifier name = new Identifier(registryName.split(":")[0], registryName.split(":")[1]);
        if(type.equals("block")) {
            RegistryUtils.register(new Block(Block.Settings.of(Material.STONE)), name);
        } else if(type.equals("item")) {
            int stackSize = JsonHelper.getInt(jsonObject, "stack_size");
            RegistryUtils.registerItem(new Item(new Item.Settings().itemGroup(ItemGroup.MISC).stackSize(stackSize)), name);
        }
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(Neutronia.MOD_ID, "modding_registry");
    }

    @Override
    public void apply(ResourceManager resourceManager) {
        Gson gson_1 = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

        for (Identifier identifier_1 : resourceManager.findResources(BOOKS_LOCATION, (s -> s.endsWith(".json")))) {
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
            } catch (IllegalArgumentException | JsonParseException | IOException var21) {
                Neutronia.getLogger().error("Parsing error loading addition information {}", identifier_2, var21);
            }
        }
    }

}