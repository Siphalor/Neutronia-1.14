package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public abstract class RedwoodFeature extends AbstractTreeFeature<DefaultFeatureConfig>
{
	public RedwoodFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1)
	{
		super(function_1, false);
	}

	public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld world, Random random_1, BlockPos blockPos_1)
	{
		int height = 25 + random_1.nextInt(45);

		blockPos_1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, blockPos_1);

		if (blockPos_1.getY() >= 1 && blockPos_1.getY() + height + 1 <= 256 && isNaturalDirtOrGrass(world, blockPos_1.down()))
		{

			BlockPos origin = blockPos_1.add(0, height - 1, 0);

			//Last 5 layers leaves
			for (int i = height - 6; i <= height; ++i)
			{
				int j = i == height - 5 ? height - 3 : i + 1;
				if (i == height - 6) j = height - 2;

				int k = height - j;

				for (int x = -k; x < k + 1; ++x)
					for (int z = -k; z < k + 1; ++z)
					{
						if (Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2)) < k)
							setBlockState(set_1, world, blockPos_1.add(x, i, z), getLeavesBlockState((IWorld) world, origin, blockPos_1));
					}
			}

			//More leaves lol
			for (int i = height / 2 - 5; i < height - 5; ++i)
			{
				int j = height < 2 * height / 3 ? 3 : 3 + random_1.nextInt(2);
				int j1 = height < 2 * height / 3 + 2 ? 3 : 4;

				int k = j + ((height - i) % j1);

				if (k < 4) k = 4;

				for (int x = -k; x < k + 1; ++x)
					for (int z = -k; z < k + 1; ++z)
					{
						if (Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2)) < k - 1)
							setBlockState(set_1, world, blockPos_1.add(x, i, z), getLeavesBlockState((IWorld) world, origin, blockPos_1));
					}
			}

			//Trunk up to height - 3
			for (int i = -2; i < height - 3; ++i)
			{
				for (int x = -1; x < 2; ++x)
					for (int z = -1; z < 2; ++z)
						setBlockState(set_1, world, blockPos_1.add(x, i, z), getLogBlockState((IWorld) world, origin, blockPos_1));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	protected abstract BlockState getLeavesBlockState(IWorld world, BlockPos origin, BlockPos pos);

	protected abstract BlockState getLogBlockState(IWorld world, BlockPos origin, BlockPos pos);

}