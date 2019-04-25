package team.hollow.neutronia.modules.exploration;

import team.hollow.module_api.api.SubModule;
import team.hollow.neutronia.modules.exploration.food.BerriesFeature;
import team.hollow.neutronia.modules.exploration.food.CakeFeature;

public class FoodSubModule extends SubModule {
	public static CakeFeature cake;
	public static BerriesFeature berries;

	public FoodSubModule() {
		super("food", "Adds various foods");

		cake = register(new CakeFeature());
		berries = register(new BerriesFeature());
	}
}
