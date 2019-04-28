package team.hollow.neutronia.init;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.world.gen.feature.*;

public class NFeatures {

	public static final Feature<DefaultFeatureConfig> DEAD_TREE_FEATURE;
	public static final Feature<DefaultFeatureConfig> LARGE_SHRUB;
	public static final Feature<DefaultFeatureConfig> LARGE_SPRUCE_SHRUB;
	public static final Feature<DefaultFeatureConfig> LARGE_SWAMP_TREE;
	public static final Feature<DefaultFeatureConfig> PALM;
	public static final Feature<DefaultFeatureConfig> REDWOOD;
	public static final Feature<DefaultFeatureConfig> SMALL_REDWOOD;
	public static final Feature<DefaultFeatureConfig> FUNGI;
	public static final Feature<DefaultFeatureConfig> WILLOW_TREE;
	public static final Feature<DefaultFeatureConfig> SMALL_BOULDER;
	public static final Feature<DefaultFeatureConfig> MEDIUM_BOULDER;
	public static final Feature<DefaultFeatureConfig> BIG_BOULDER;

	static {
		DEAD_TREE_FEATURE = register("dead_tree", new DeadTreeFeature(DefaultFeatureConfig::deserialize));
		LARGE_SWAMP_TREE = register("large_swamp_tree", new LargeSwampTreeFeature(DefaultFeatureConfig::deserialize));
		LARGE_SHRUB = register("large_shrub", new LargeShrubFeature(Blocks.OAK_LEAVES.getDefaultState(), DefaultFeatureConfig::deserialize));
		LARGE_SPRUCE_SHRUB = register("large_spruce_shrub", new LargeShrubFeature(Blocks.SPRUCE_LEAVES.getDefaultState(), DefaultFeatureConfig::deserialize));
		REDWOOD = register("redwood", new RedwoodFeature(DefaultFeatureConfig::deserialize));
		PALM = register("palm", new PalmTreeFeature(DefaultFeatureConfig::deserialize));
		SMALL_REDWOOD = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize));
		FUNGI = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize));
		WILLOW_TREE = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize));
		SMALL_BOULDER = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize));
		MEDIUM_BOULDER = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize));
		BIG_BOULDER = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize));
	}

	public static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
		return Registry.register(Registry.FEATURE, new Identifier(Neutronia.MOD_ID, string_1), feature_1);
	}

}