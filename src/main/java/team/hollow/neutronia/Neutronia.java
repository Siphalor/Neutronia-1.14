package team.hollow.neutronia;

import generators.ContentResourceBuilder;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.module_api.ModuleManager;
import team.hollow.neutronia.modules.*;
import team.hollow.neutronia.registry.BlockChiseler;
import team.hollow.neutronia.registry.ContentBuilder;
import team.hollow.neutronia.registry.ContentRegistryBuilder;
import team.hollow.quest_api.QuestManager;
import team.hollow.quest_api.api.Quest;
import team.hollow.quest_api.api.QuestReward;
import team.hollow.quest_api.api.QuestTask;

public class Neutronia implements ModInitializer {

    public static final boolean GEN_RESOURCES = System.getProperty("genResources") != null;

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        if(GEN_RESOURCES) {
            ContentBuilder.setInstance(new ContentResourceBuilder(Neutronia.MOD_ID));
        } else {
            ContentBuilder.setInstance(new ContentRegistryBuilder(Neutronia.MOD_ID));
        }

        setupModules();

        ContentBuilder.getInstance().finish();

        if(GEN_RESOURCES)
            System.exit(0);

        BlockChiseler.setup();

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
//        new NBlocks();
//        new NLightBlocks();
        //NBlockEntities.init();
        //new NEntityTypes();
        //new NPaintingMotives();
        //new NBiomes();
        //new NVillagers();
        //CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        //CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        //CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        //ModMenuBadgeManager.registerBadges(MOD_ID, ModMenuBadges.ALPHA, ModMenuBadges.BETA, new NeutroniaBadge());
        //VersionChecker.startVersionCheck();
    }

    public static void setupModules() {
        ModuleManager.registerModule(new OriginsModule());
        ModuleManager.registerModule(new EndecorationsModule());
        ModuleManager.registerModule(new ExplorationModule());
        ModuleManager.registerModule(new VariationModule());
        ModuleManager.registerModule(new VillagesAndVillagersModule());
        ModuleManager.setup();
    }

}