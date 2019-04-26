package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.modules.origins.StorageSubModule;

public class OriginsModule extends Module {
	public static StorageSubModule storage;

	public OriginsModule() {
		super("origins", "This module bundles early-game features");

		storage = register(new StorageSubModule());
		setBackgroundTexture(new Identifier("minecraft", "textures/block/sand.png"));
	}
}