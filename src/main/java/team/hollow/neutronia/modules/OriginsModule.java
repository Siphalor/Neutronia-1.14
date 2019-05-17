package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.abnormalib.modules.api.MainModule;
import team.hollow.neutronia.modules.origins.CompostablesFeature;
import team.hollow.neutronia.modules.origins.StorageSubModule;

public class OriginsModule extends MainModule {
	public static StorageSubModule storage;
	public static CompostablesFeature compostables;

	public OriginsModule() {
		super("origins", "This module bundles early-game features");

		storage = register(new StorageSubModule());
		compostables = register(new CompostablesFeature());

		setBackgroundTexture(new Identifier("minecraft", "textures/block/sand.png"));
	}
}