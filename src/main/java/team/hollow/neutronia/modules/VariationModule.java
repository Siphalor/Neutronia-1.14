package team.hollow.neutronia.modules;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.modules.variation.*;

public class VariationModule extends Module {
	public static StairsAndSlabsFeature stairsAndSlabs;
	public static FencesAndWallsFeature fencesAndWalls;
	public static SidingsFeature sidings;

	public static WoodSubModule woodSubModule;

	public static PottedPlantsFeature pottedPlants;

	public VariationModule() {
		super("variation", "This module contains variations for existing vanilla features.");

		stairsAndSlabs = register(new StairsAndSlabsFeature());
		fencesAndWalls = register(new FencesAndWallsFeature());
		sidings = register(new SidingsFeature());

		woodSubModule = register(new WoodSubModule());

		pottedPlants = register(new PottedPlantsFeature());

		setBackgroundTexture(new Identifier("minecraft", "textures/block/oak_log.png"));
	}
}
