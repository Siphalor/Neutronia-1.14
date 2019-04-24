package team.hollow.neutronia.modules;

import de.siphalor.tweed.config.ConfigEnvironment;
import de.siphalor.tweed.config.ConfigScope;
import de.siphalor.tweed.config.entry.BooleanEntry;
import team.hollow.module_api.api.Module;

public class DecorationModule extends Module {
	public static BooleanEntry enableStairsAndSlabs = new BooleanEntry(true)
		.setEnvironment(ConfigEnvironment.SERVER)
		.setScope(ConfigScope.GAME)
		.setComment("Enable stairs and slabs");
	public static BooleanEntry enableFences = new BooleanEntry(true)
		.setEnvironment(ConfigEnvironment.SERVER)
		.setScope(ConfigScope.GAME)
		.setComment("Enable fences and fence gates");
	public static BooleanEntry enableRedstoneyBlocks = new BooleanEntry(true)
		.setEnvironment(ConfigEnvironment.SERVER)
		.setScope(ConfigScope.GAME)
		.setComment("Enable buttons and pressure plates");
	public static BooleanEntry enableSidings = new BooleanEntry(true)
		.setEnvironment(ConfigEnvironment.SERVER)
		.setScope(ConfigScope.GAME)
		.setComment("Enable sidings and corners");

	public DecorationModule() {
		super("building", "This module contains various building and decoration related configurations.");

		register("enable-stairs-and-slabs", enableStairsAndSlabs);
		register("enable-fences", enableFences);
		register("enable-buttons-and-plates", enableFences);
		register("enable-sidings", enableSidings);
	}
}
