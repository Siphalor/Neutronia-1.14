package team.hollow.neutronia;

import me.sargunvohra.mcmods.autoconfig.api.AutoConfig;
import me.sargunvohra.mcmods.autoconfig.api.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.modmenu_api.ModMenuBadgeManager;
import team.hollow.modmenu_api.api.ModMenuBadges;
import team.hollow.neutronia.init.*;
import team.hollow.update_checker_api.VersionChecker;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static ModConfig config;

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(MOD_ID, ModConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.<ModConfig>getConfigHolder(MOD_ID).getConfig();
        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        new NEntityTypes();
        new NPaintingMotives();
        new NBiomes();
        new NVillagers();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        ModMenuBadgeManager.registerBadges(MOD_ID, ModMenuBadges.ALPHA, ModMenuBadges.BETA, new NeutroniaBadge());
        VersionChecker.startVersionCheck();
    }

}