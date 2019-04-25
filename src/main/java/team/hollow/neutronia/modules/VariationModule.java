package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.modules.variation.FencesFeature;
import team.hollow.neutronia.modules.variation.SidingsFeature;
import team.hollow.neutronia.modules.variation.StairsAndSlabsFeature;
import team.hollow.neutronia.modules.variation.WoodSubModule;

public class VariationModule extends Module {
	public static StairsAndSlabsFeature stairsAndSlabs;
	public static FencesFeature fences;
	public static SidingsFeature sidings;

	public static WoodSubModule woodSubModule;

	public VariationModule() {
		super("variation", "This module contains variations for existing vanilla features.");

		woodSubModule = register(new WoodSubModule());
		setBackgroundTexture(new Identifier("minecraft", "textures/block/birch_log.png"));
	}
}
