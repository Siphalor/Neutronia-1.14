package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import team.abnormals.neutronia.commands.Locate2Command;
import team.abnormals.neutronia.init.*;
import team.abnormals.neutronia.world.gen.features.OreGeneration;

public class Neutronia implements ModInitializer {

    public static final String MODID = "neutronia";

    @Override
    public void onInitialize() {
        new NBlocks();
        NItems.init();
        NBlockEntities.init();
        CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
        ModVillagers.init();
        NEntityTypes.init();
        NRecipeType.init();
        NRecipeSerializers.init();
        OreGeneration.registerOres();
        NRecipes.init();
    }

}