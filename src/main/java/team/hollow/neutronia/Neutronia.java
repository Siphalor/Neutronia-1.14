package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.abnormalib.blocks.BaseModBlock;
import team.hollow.abnormalib.modules.ModuleManager;
import team.hollow.abnormalib.utils.ContentBuilder;
import team.hollow.abnormalib.utils.ContentRegistryBuilder;
import team.hollow.abnormalib.utils.generators.ContentResourceBuilder;
import team.hollow.abnormalib.utils.registry.BlockChiseler;
import team.hollow.abnormalib.utils.registry.WoodTypeRegistry;
import team.hollow.neutronia.modules.*;
import team.hollow.quest_api.QuestManager;
import team.hollow.quest_api.api.Quest;
import team.hollow.quest_api.api.QuestReward;
import team.hollow.quest_api.api.QuestTask;

public class Neutronia implements ModInitializer {

    public static final boolean GEN_RESOURCES = System.getProperty("genResources") != null;

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);
    public static final ModuleManager MODULE_MANAGER = new ModuleManager(MOD_ID);

    private static ContentBuilder contentBuilder;

    public static Logger getLogger() {
        return LOGGER;
    }
    public static ContentBuilder getContentBuilder() {
        return contentBuilder;
    }

    @Override
    public void onInitialize() {
        if(GEN_RESOURCES) {
            contentBuilder = new ContentResourceBuilder(MOD_ID);
        } else {
            contentBuilder = new ContentRegistryBuilder(MOD_ID);
        }

        WoodTypeRegistry.registerModdedTypeListener((woodType, hardness, resistance) -> {
            woodType.baseBlock = contentBuilder.newBaseBlock(woodType.getIdentifier().getPath() + "_planks", new BaseModBlock(
                FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).hardness(hardness).resistance(resistance)
            ));

            contentBuilder.setBaseName(woodType.getIdentifier());
            contentBuilder.slab();
            contentBuilder.stairs();
            contentBuilder.button(true);
            contentBuilder.pressurePlate(PressurePlateBlock.ActivationRule.EVERYTHING);
            contentBuilder.fence();
            contentBuilder.fenceGate();
            contentBuilder.door();
            contentBuilder.trapDoor();
            contentBuilder.setSecondaryItem(Items.STICK);
            contentBuilder.sign();
        });

        setupModules();

        contentBuilder.finish();

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
    	MODULE_MANAGER.registerModules(new OriginsModule(), new EndecorationsModule(), new ExplorationModule(), new VariationModule(), new VillagesAndVillagersModule());
        MODULE_MANAGER.setup();
    }

}