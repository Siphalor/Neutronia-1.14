package team.hollow.neutronia;

import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.command.FindBiomeCommand;
import team.hollow.neutronia.init.*;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static ModConfig config;
    public static ExampleConfig exampleConfig;

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        AutoConfig.register(ExampleConfig.class, JanksonConfigSerializer::new);
        exampleConfig = AutoConfig.getConfigHolder(ExampleConfig.class).getConfig();
        new NBlocks();
        new NLightBlocks();
        new NItems();
        new NBlockEntities();
        new NEntityTypes();
        new NPaintingMotives();
        new NBiomes();
        new NVillagers();
        CompostingChanceRegistry.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        CommandRegistry.INSTANCE.register(false, FindBiomeCommand::register);
    }

}