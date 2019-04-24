package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.modmenu_api.ModMenuBadgeManager;
import team.hollow.modmenu_api.api.ModMenuBadges;
import team.hollow.module_api.ModuleManager;
import team.hollow.neutronia.init.*;
import team.hollow.neutronia.modules.TreeModule;
import team.hollow.quest_api.QuestManager;
import team.hollow.quest_api.api.Quest;
import team.hollow.quest_api.api.QuestReward;
import team.hollow.quest_api.api.QuestTask;
import team.hollow.update_checker_api.VersionChecker;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        ModuleManager.registerModule(new TreeModule());
    	ModuleManager.setup();

        QuestManager.registerQuests(
                new Quest(
                        new Identifier(MOD_ID, "test"),
                        new ItemStack(Items.DIAMOND_PICKAXE),
                        new QuestTask(
                                "Carrot Collector",
                                "Help me get some carrots",
                                new QuestReward(
                                        new ItemStack(Items.EMERALD, 3),
                                        10
                                )
                        )
                )
        );
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