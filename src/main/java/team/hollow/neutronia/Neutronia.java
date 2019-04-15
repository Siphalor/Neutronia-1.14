package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.configNew.ConfigManager;
import team.hollow.neutronia.event_system.EventCore;
import team.hollow.neutronia.init.*;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    public static final String PREFIX = MOD_ID + ":";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static TestConfig testConfig;

    static {
        EventCore.instance = new EventCore();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        testConfig = ConfigManager.loadConfig(TestConfig.class);
        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        new NEntityTypes();
        new NPaintingMotives();
        new NBiomes();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);
    }

}