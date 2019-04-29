package team.hollow.neutronia.init;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.world.gen.feature.*;

import java.util.Random;

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
		REDWOOD = register("redwood", new RedwoodFeature(DefaultFeatureConfig::deserialize) {
			@Override
			protected BlockState getLeavesBlockState(IWorld world, BlockPos origin, BlockPos pos) {
				return Blocks.DARK_OAK_LEAVES.getDefaultState();
			}

			@Override
			protected BlockState getLogBlockState(IWorld world, BlockPos origin, BlockPos pos) {
				return Blocks.DARK_OAK_LOG.getDefaultState();
			}
		});
		PALM = register("palm", new PalmTreeFeature(DefaultFeatureConfig::deserialize) {
			@Override
			protected BlockState getLeavesBlockState(IWorld world, BlockPos origin, BlockPos pos) {
				return Blocks.JUNGLE_LEAVES.getDefaultState();
			}

			@Override
			protected BlockState getLogBlockState(IWorld world, BlockPos origin, BlockPos pos) {
				return Blocks.JUNGLE_LOG.getDefaultState();
			}
		});
		SMALL_REDWOOD = register("small_redwood", new SmallRedwoodFeature(DefaultFeatureConfig::deserialize) {
			@Override
			protected BlockState getLeavesBlockState(IWorld world, BlockPos origin, BlockPos pos) {
				return Blocks.DARK_OAK_LEAVES.getDefaultState();
			}

			@Override
			protected BlockState getLogBlockState(IWorld world, BlockPos origin, BlockPos pos) {
				return Blocks.DARK_OAK_LOG.getDefaultState();
			}
		});
		FUNGI = register("small_redwood", new FungiFeature());
		WILLOW_TREE = register("small_redwood", new WillowTreeFeature(DefaultFeatureConfig::deserialize, false));
		SMALL_BOULDER = register("small_redwood", new BoulderFeature(3) {
			@Override
			protected BlockState getStateForPlacement(IWorld world, BlockPos origin, BlockPos pos, double dist, float radiusSquare, Random random) {
				return Blocks.COARSE_DIRT.getDefaultState();
			}
		});
		MEDIUM_BOULDER = register("small_redwood", new BoulderFeature(7) {
			@Override
			protected BlockState getStateForPlacement(IWorld world, BlockPos origin, BlockPos pos, double dist, float radiusSquare, Random random) {
				return Blocks.COARSE_DIRT.getDefaultState();
			}
		});
		BIG_BOULDER = register("small_redwood", new BoulderFeature(10) {
			@Override
			protected BlockState getStateForPlacement(IWorld world, BlockPos origin, BlockPos pos, double dist, float radiusSquare, Random random) {
				return Blocks.COARSE_DIRT.getDefaultState();
			}
		});
	}

	public static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
		return Registry.register(Registry.FEATURE, new Identifier(Neutronia.MOD_ID, string_1), feature_1);
	}

}