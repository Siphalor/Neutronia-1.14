package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.api.groups.GroupLoader;
import team.hollow.neutronia.configNew.ConfigManager;
import team.hollow.neutronia.event_system.EventCore;
import team.hollow.neutronia.init.*;
import team.hollow.neutronia.network.NotebookUpdatePacket;
import team.hollow.neutronia.notebook.NotebookFolderLoader;
import team.hollow.neutronia.notebook.NotebookRegistry;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    public static final String PREFIX = MOD_ID + ":";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static TestConfig testConfig;

    private static team.hollow.neutronia.client.ConfigManager configManager;

    static {
        EventCore.instance = new EventCore();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        testConfig = ConfigManager.loadConfig(TestConfig.class);
        configManager = new team.hollow.neutronia.client.ConfigManager(testConfig);

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

        GroupLoader.init();

        ServerSidePacketRegistry.INSTANCE.register(NotebookUpdatePacket.ID, new NotebookUpdatePacket.Handler());
    }

    public static team.hollow.neutronia.client.ConfigManager getConfigManager() {
        return configManager;
    }

    /*public static void openConfigScreen(Screen parent) {
        ConfigScreenBuilder builder = new ClothConfigScreen.Builder(parent, I18n.translate("text.neutronia.config.title"), null);
        ConfigScreenBuilder.CategoryBuilder common = builder.addCategory("Common");
        common.addOption(new BooleanListEntry("Do Mini Biomes",
                getConfigManager().getConfig().common.doMiniBiomes, "text.cloth.reset_value",
                () -> false, (bool) -> getConfigManager().getConfig().common.doMiniBiomes = bool));
        ConfigScreenBuilder.CategoryBuilder client = builder.addCategory("Client");
        client.addOption(new BooleanListEntry("Display config button in settings",
                getConfigManager().getConfig().client.displayConfigButton, "text.cloth.reset_value",
                () -> false, (bool) -> getConfigManager().getConfig().client.displayConfigButton = bool));
        client.addOption(new BooleanListEntry("Displays more information on the main menu",
                getConfigManager().getConfig().client.mainMenuExtra, "text.cloth.reset_value",
                () -> true, (bool) -> getConfigManager().getConfig().client.mainMenuExtra = bool));
        client.addOption(new BooleanListEntry("Display more information on the splash screen",
                getConfigManager().getConfig().client.splashScreenExtra, "text.cloth.reset_value",
                () -> true, (bool) -> getConfigManager().getConfig().client.splashScreenExtra = bool));
        builder.setOnSave((savedConfig) -> ConfigManager.saveConfig(testConfig));
        MinecraftClient.getInstance().openScreen(builder.build());
    }*/

}