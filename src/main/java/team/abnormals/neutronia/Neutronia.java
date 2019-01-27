package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.gen.feature.Feature;
import team.abnormals.neutronia.init.*;

import java.util.Locale;

public class Neutronia implements ModInitializer {

	@Override
	public void onInitialize() {
		CarvedPlanks.init();
		FrostedGlass.init();
        StorageBlocks.init();
        NBlocks.init();
        NItems.init();
        NBlockEntities.init();
//		CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
		ModVillagers.init();
		NEntityTypes.init();
		NRecipeType.init();
		NRecipeSerializers.init();

        Feature.STRUCTURES.put("Pillager_Outpost_Test".toLowerCase(Locale.ROOT), NFeatures.PILLAGER_OUTPOST);
	}

}