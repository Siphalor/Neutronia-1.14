package team.hollow.neutronia;

import com.google.gson.GsonBuilder;
import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.ClothConfigScreen;
import me.shedaniel.cloth.gui.entries.BooleanListEntry;
import me.shedaniel.rei.RoughlyEnoughItemsCore;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.api.groups.GroupLoader;
import team.hollow.neutronia.config.ConfigFile;
import team.hollow.neutronia.configNew.ConfigManager;
import team.hollow.neutronia.event_system.EventCore;
import team.hollow.neutronia.init.*;
import team.hollow.neutronia.network.NotebookUpdatePacket;
import team.hollow.neutronia.notebook.NotebookFolderLoader;
import team.hollow.neutronia.notebook.NotebookRegistry;
import team.hollow.neutronia.utils.JsonConfig;
import team.hollow.neutronia.utils.WailaConfig;

import java.io.File;
import java.io.IOException;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    public static final String PREFIX = MOD_ID + ":";
    public static final JsonConfig<WailaConfig> CONFIG = new JsonConfig<>("neutronia/neutronia", WailaConfig.class).withGson((new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(Identifier.class, new Identifier.DeSerializer()).create());
    public static final File CONFIG_DIRECTORY = new File(FabricLoader.getInstance().getConfigDirectory(), MOD_NAME);
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);
    public static final File DATA_PACK_LOCATION = new File(FabricLoader.getInstance().getGameDirectory(), "datapacks/neutronia_books/");

    public static ConfigFile neutroniaCommonConfig;
    public static TestConfig testConfig;

    private static team.hollow.neutronia.client.ConfigManager configManager;

    static {
        EventCore.instance = new EventCore();
        CONFIG_DIRECTORY.mkdirs();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        configManager = new team.hollow.neutronia.client.ConfigManager();
//        Config.loadConfig(Config.CLIENT_CONFIG, CONFIG_DIRECTORY.toPath().resolve("client.toml"));
//        Config.loadConfig(Config.SERVER_CONFIG, CONFIG_DIRECTORY.toPath().resolve("common.toml"));

        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        new NEntityTypes();
        new NPaintingMotives();
        new NGroups();
        new NBiomes();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        NotebookFolderLoader.setup(FabricLoader.getInstance().getConfigDirectory().getParentFile().getParentFile());
        NotebookRegistry.INSTANCE.init();

        neutroniaCommonConfig = new ConfigFile(MOD_ID + "_common", "config.neutronia.common", ModConfig.Common.class);
        neutroniaCommonConfig.loadConfig();

        testConfig = ConfigManager.loadConfig(TestConfig.class);

        GroupLoader.init();

        ServerSidePacketRegistry.INSTANCE.register(NotebookUpdatePacket.ID, new NotebookUpdatePacket.Handler());
    }

    public static team.hollow.neutronia.client.ConfigManager getConfigManager() {
        return configManager;
    }

    public static void openConfigScreen(Screen parent) {
        ConfigScreenBuilder builder = new ClothConfigScreen.Builder(parent, I18n.translate("text.neutronia.config.title"), null);
        ConfigScreenBuilder.CategoryBuilder common = builder.addCategory("Common");
        common.addOption(new BooleanListEntry("text.rei.config.cheating",
                RoughlyEnoughItemsCore.getConfigManager().getConfig().cheating, "text.cloth.reset_value",
                () -> false, (bool) -> RoughlyEnoughItemsCore.getConfigManager().getConfig().cheating = bool));
        common.addOption(new BooleanListEntry("fish is good",
                RoughlyEnoughItemsCore.getConfigManager().getConfig().fish, "text.cloth.reset_value",
                () -> true, (bool) -> RoughlyEnoughItemsCore.getConfigManager().getConfig().fish = bool));
        ConfigScreenBuilder.CategoryBuilder client = builder.addCategory("Client");
        client.addOption(new BooleanListEntry("",
                RoughlyEnoughItemsCore.getConfigManager().getConfig().cheating, "text.cloth.reset_value",
                () -> false, (bool) -> RoughlyEnoughItemsCore.getConfigManager().getConfig().cheating = bool));
        client.addOption(new BooleanListEntry("text.rei.config.cheating",
                RoughlyEnoughItemsCore.getConfigManager().getConfig().cheating, "text.cloth.reset_value",
                () -> false, (bool) -> RoughlyEnoughItemsCore.getConfigManager().getConfig().cheating = bool));
        client.addOption(new BooleanListEntry("Display config button",
                getConfigManager().getConfig().client.displayConfigButton, "text.cloth.reset_value",
                () -> true, (bool) -> getConfigManager().getConfig().client.displayConfigButton = bool));
        builder.setOnSave((savedConfig) -> {
            try {
                Neutronia.getConfigManager().saveConfig();
            } catch (IOException var2) {
                var2.printStackTrace();
            }

        });
        MinecraftClient.getInstance().openScreen(builder.build());
    }

}