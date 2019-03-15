package team.hollow.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.impl.registry.CompostingChanceRegistryImpl;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.hollow.neutronia.commands.Locate2Command;
import team.hollow.neutronia.init.*;
import team.hollow.neutronia.world.SmallStructuresRegistry;
import team.hollow.neutronia.world.gen.features.RitualSiteFeatureConfig;

public class Neutronia implements ModInitializer {

    public static final String MOD_ID = "neutronia";
    public static final String MOD_NAME = "Neutronia";
    private static final Logger LOGGER = LogManager.getFormatterLogger(MOD_NAME);

    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public void onInitialize() {
        Feature.STRUCTURES.put("ritual_site", SmallStructuresRegistry.RITUAL_SITE_FEATURE);
        for(Biome b: Registry.BIOME) {
            if(b.getCategory() == Biome.Category.TAIGA) {
                b.addStructureFeature(SmallStructuresRegistry.RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1));
                b.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(SmallStructuresRegistry.RITUAL_SITE_FEATURE, new RitualSiteFeatureConfig(1),
                        Decorator.NOPE, new NopeDecoratorConfig()));
            }
        }

        new NBlocks();
        new NLightBlocks();
        new NItems();
        NBlockEntities.init();
        CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
        new NEntityTypes();
        new NPaintingMotives();
        CompostingChanceRegistryImpl.INSTANCE.add(Items.ROTTEN_FLESH, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.CHICKEN, 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(Items.COOKED_CHICKEN, 0.5F);

        System.out.println("+----------+ Mod Initialized");
    }

}