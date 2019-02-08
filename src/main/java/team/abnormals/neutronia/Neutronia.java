package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import team.abnormals.neutronia.init.*;

public class Neutronia implements ModInitializer {

	@Override
	public void onInitialize() {
		CarvedPlanks.init();
		FrostedGlass.init();
        StorageBlocks.init();
        new NBlocks();
        NItems.init();
        NBlockEntities.init();
		ModVillagers.init();
		NEntityTypes.init();
		NRecipeType.init();
		NRecipeSerializers.init();
	}

}