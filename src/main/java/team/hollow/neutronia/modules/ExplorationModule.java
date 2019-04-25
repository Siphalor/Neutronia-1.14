package team.hollow.neutronia.modules;

import team.hollow.module_api.api.Module;
import team.hollow.neutronia.modules.exploration.TreesSubModule;

@SuppressWarnings("WeakerAccess")
public class ExplorationModule extends Module {

	TreesSubModule trees;

	public ExplorationModule() {
		super("exploration", "This module contains exploration and world generation related features");

		trees = register(new TreesSubModule());
	}
}
