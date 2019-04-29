package team.hollow.neutronia.world;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import team.hollow.neutronia.init.NFeatures;

public class CustomBiomeFeatures
{

	public static void addGrasslandFeatures(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.PLAIN_FLOWER, FeatureConfig.DEFAULT, Decorator.NOISE_HEIGHTMAP_32, new NoiseHeightmapDecoratorConfig(-0.8D, 10, 4)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Decorator.NOISE_HEIGHTMAP_DOUBLE, new NoiseHeightmapDecoratorConfig(-0.8D, 5, 10)));
	}

	public static void addBayouFeatures(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.SWAMP_TREE}, new FeatureConfig[]{FeatureConfig.DEFAULT}, new float[]{0.2F}, NFeatures.LARGE_SWAMP_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(8, 0.1F, 1)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.SWAMP_FLOWER, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_32, new CountDecoratorConfig(1)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(3)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.WATERLILY, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(4)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(Blocks.BROWN_MUSHROOM.getDefaultState()), Decorator.COUNT_CHANCE_HEIGHTMAP, new CountChanceDecoratorConfig(10, 0.35F)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(Blocks.RED_MUSHROOM.getDefaultState()), Decorator.COUNT_CHANCE_HEIGHTMAP_DOUBLE, new CountChanceDecoratorConfig(10, 0.175F)));
	}

	public static void addIncreasedMushrooms(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(Blocks.BROWN_MUSHROOM.getDefaultState()), Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(7)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.BUSH, new BushFeatureConfig(Blocks.RED_MUSHROOM.getDefaultState()), Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(14)));
	}

	public static void addIncreasedWaterLakes(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, Biome.configureFeature(Feature.LAKE, new LakeFeatureConfig(Blocks.WATER.getDefaultState()), Decorator.WATER_LAKE, new LakeDecoratorConfig(2)));
	}

	public static void addBrushlandTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.SAVANNA_TREE, NFeatures.DEAD_TREE_FEATURE, Feature.DARK_OAK_TREE}, createFeatureConfig(3), new float[]{0.08F, 0.2F, 0.08F}, NFeatures.SHRUB, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(8, 0.1F, 1)));
	}

	public static void addChapparalShrubs(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NFeatures.SHRUB, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(6, 0.1F, 1)));
	}

	public static void addShieldTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.FANCY_TREE}, new FeatureConfig[]{FeatureConfig.DEFAULT}, new float[]{0.05F}, Feature.SPRUCE_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(10, 0.1F, 1)));
	}

	public static void addGroveTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.FANCY_TREE}, new FeatureConfig[]{FeatureConfig.DEFAULT}, new float[]{0.5F}, Feature.PINE_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(2, 0.1F, 1)));
	}

	public static void addRainforestVegetation(Biome biome_1) {
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.MELON, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)));
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.VINES, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHT_64, new CountDecoratorConfig(20)));
	}

	public static void addRainforestTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.FANCY_TREE, NFeatures.EMPTY_FEATURE, Feature.MEGA_JUNGLE_TREE}, new FeatureConfig[]{FeatureConfig.DEFAULT, FeatureConfig.DEFAULT, FeatureConfig.DEFAULT}, new float[]{0.02F, 0.3F, 0.3F}, Feature.JUNGLE_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(20, 0.3F, 10)));
	}

	public static void addPalms(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NFeatures.PALM, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.1F, 1)));
	}

	public static void addExtraPalms(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NFeatures.PALM, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(2, 0.1F, 1)));
	}

	public static void addSubtropicalRainforestTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.FANCY_TREE, Feature.MEGA_PINE_TREE, Feature.PINE_TREE, Feature.MEGA_JUNGLE_TREE}, createFeatureConfig(4), new float[]{0.15F, 0.2F, 0.35F, 0.03F}, Feature.JUNGLE_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(10, 0.3F, 10)));
	}

	public static void addShrublandShrubs(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NFeatures.LARGE_SHRUB, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(2, 0.1F, 1)));
	}

	public static void addSteppeTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.SPRUCE_TREE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(0, 0.05F, 1)));
	}

	public static void addTemperateRainforestTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{NFeatures.LARGE_BOREAL_FEATURE, NFeatures.BOREAL_FEATURE, Feature.SPRUCE_TREE, Feature.PINE_TREE, NFeatures.LARGE_SHRUB}, new FeatureConfig[]{FeatureConfig.DEFAULT, FeatureConfig.DEFAULT, FeatureConfig.DEFAULT, FeatureConfig.DEFAULT, FeatureConfig.DEFAULT}, new float[]{0.03F, 0.3F, 0.2F, 0.25F, 0.15F}, Feature.FANCY_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(6, 0.3F, 6)));
	}

	public static void addMarshShrubs(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NFeatures.LARGE_SHRUB, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.7F, 2)));
	}

	public static void addFenTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.PINE_TREE, Feature.SPRUCE_TREE}, new FeatureConfig[]{FeatureConfig.DEFAULT, FeatureConfig.DEFAULT}, new float[]{0.1F, 0.2F}, NFeatures.DEAD_TREE_FEATURE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(0, 0.35F, 1)));
	}

	public static void addExtraFenTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{Feature.PINE_TREE, Feature.SPRUCE_TREE}, new FeatureConfig[]{FeatureConfig.DEFAULT, FeatureConfig.DEFAULT}, new float[]{0.25F, 0.15F}, NFeatures.DEAD_TREE_FEATURE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(3, 0.1F, 1)));
	}

	public static void addMireTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(NFeatures.DEAD_TREE_FEATURE, FeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(4, 0.3F, 2)));
	}
	
	public static void addBorealTrees(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(new Feature[]{NFeatures.BOREAL_FEATURE, NFeatures.LARGE_SPRUCE_SHRUB}, createFeatureConfig(2), new float[]{0.32F, 0.1F}, Feature.SPRUCE_TREE, FeatureConfig.DEFAULT), Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(4, 0.1F, 1)));
	}
	
	public static void addWaterlillies(Biome biome_1)
	{
		biome_1.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.WATERLILY, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(4)));
	}
	
	private static FeatureConfig[] createFeatureConfig(int size)
	{
		FeatureConfig[] config = new FeatureConfig[size];
		
		for (int i = 0; i < size; ++i) config[i] = FeatureConfig.DEFAULT;
		return config;
	}
}