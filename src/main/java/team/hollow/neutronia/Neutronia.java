package team.hollow.neutronia;

import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.api.groups.GroupLoader;
import team.hollow.neutronia.commands.Locate2Command;
import team.hollow.neutronia.config.ConfigFile;
import team.hollow.neutronia.event_system.EventCore;
import team.hollow.neutronia.init.*;
import team.hollow.neutronia.utils.JsonConfig;
import team.hollow.neutronia.utils.WailaConfig;

import java.io.File;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static final JsonConfig<WailaConfig> CONFIG = new JsonConfig<>("neutronia/neutronia", WailaConfig.class).withGson((new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(Identifier.class, new Identifier.DeSerializer()).create());

    public static ConfigFile generalConfigFile;

    public static final File CONFIG_DIRECTORY = new File(FabricLoader.getInstance().getConfigDirectory(), MOD_NAME);

    static {
        EventCore.instance = new EventCore();
        CONFIG_DIRECTORY.mkdirs();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        Config.loadConfig(Config.CLIENT_CONFIG, CONFIG_DIRECTORY.toPath().resolve("client.toml"));
        Config.loadConfig(Config.SERVER_CONFIG, CONFIG_DIRECTORY.toPath().resolve("common.toml"));

        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
        new NEntityTypes();
        new NPaintingMotives();
        new NGroups();
        new NBiomes();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        generalConfigFile = new ConfigFile(MOD_ID, "config.neutronia.general", ModConfig.class);
        generalConfigFile.loadConfig();

        GroupLoader.init();

        System.out.println("+----------+ Mod Initialized");
    }

}