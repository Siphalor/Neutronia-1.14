package team.hollow.neutronia.modules;

import team.hollow.module_api.api.Module;
import team.hollow.module_api.api.OptionalFeature;
import team.hollow.neutronia.modules.decoration.WoodSubModule;

public class DecorationModule extends Module {
	public static OptionalFeature stairsAndSlabs;
	public static OptionalFeature fences;
	public static OptionalFeature redstoneyBlocks;
	public static OptionalFeature sidings;

	public static WoodSubModule woodSubModule;

	public DecorationModule() {
		super("decorations", "This module contains various building and decoration related configurations.");

		stairsAndSlabs = register(new OptionalFeature("stairs-and-slabs", "Adds loads of stairs and slabs"));
		fences = register(new OptionalFeature("fences", "Adds loads of fences and fence gates"));
		redstoneyBlocks = register(new OptionalFeature("buttons-and-plates", "Adds loads of buttons and pressure plates"));
		sidings = register(new OptionalFeature("sidings", "Adds loads of sideways blocks and corners"));

		woodSubModule = register(new WoodSubModule());
	}
}
