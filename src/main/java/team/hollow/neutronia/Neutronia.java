package team.hollow.neutronia;

import me.sargunvohra.mcmods.autoconfig.api.AutoConfig;
import me.sargunvohra.mcmods.autoconfig.api.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.modmenu_api.ModMenuBadgeManager;
import team.hollow.modmenu_api.api.ModMenuBadges;
import team.hollow.module_api.ModuleManager;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.init.*;
import team.hollow.quest_api.QuestManager;
import team.hollow.quest_api.api.Quest;
import team.hollow.quest_api.api.QuestReward;
import team.hollow.quest_api.api.QuestTask;
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

        ModuleManager.registerModule(new Identifier(MOD_ID, "test"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test1"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test1")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test2"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test2")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test3"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test3")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test4"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test4")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test5"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test5")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test6"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test6")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test7"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test7")));
        ModuleManager.registerModule(new Identifier(MOD_ID, "test8"), new Module(new ItemStack(Items.ROTTEN_FLESH), new TranslatableTextComponent("module.neutronia.test8")));

        ModuleManager.getModulesMap().forEach((identifier, modules) -> {
            for(Module module : modules) {
                System.out.println(String.format("Module '%s' is %s", identifier.toString(), module.enabled ? "enabled" : "disabled"));
            }
        });
    }

}