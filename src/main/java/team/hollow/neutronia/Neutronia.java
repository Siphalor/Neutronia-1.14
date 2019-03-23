package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.api.groups.GroupLoader;
import team.hollow.neutronia.commands.Locate2Command;
import team.hollow.neutronia.config.ConfigFile;
import team.hollow.neutronia.init.*;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static ConfigFile generalConfigFile;

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
        new NEntityTypes();
        new NPaintingMotives();
        new NGroups();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        generalConfigFile = new ConfigFile(MOD_ID, "config.neutronia.general", ModConfig.class);
        generalConfigFile.loadConfig();

        GroupLoader.init(this);

        System.out.println("+----------+ Mod Initialized");
    }

}