package team.abnormals.neutronia;

import net.fabricmc.api.ModInitializer;
import team.abnormals.neutronia.init.*;

public class Neutronia implements ModInitializer {

	@Override
	public void onInitialize() {
		CarvedPlanks.init();
		FrostedGlass.init();
        StorageBlocks.init();
        NBlocks.init();
        NBlockEntities.init();
//		CommandRegistry.INSTANCE.register(false, (Locate2Command::register));
		ModVillagers.init();
		NEntityTypes.init();
	}

}