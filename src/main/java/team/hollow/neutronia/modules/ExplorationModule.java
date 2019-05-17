package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.abnormalib.modules.api.MainModule;
import team.hollow.neutronia.modules.exploration.FoodSubModule;
import team.hollow.neutronia.modules.exploration.TreesSubModule;

@SuppressWarnings("WeakerAccess")
public class ExplorationModule extends MainModule {

	TreesSubModule trees;
	FoodSubModule food;

	public ExplorationModule() {
		super("exploration", "This module contains exploration and world generation related features");

		trees = register(new TreesSubModule());
		food = register(new FoodSubModule());
		setBackgroundTexture(new Identifier("minecraft", "textures/block/grass_block_top.png"));
	}
}
