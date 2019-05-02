package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class LargeShrubFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
    private static final int[][] coords = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static final BlockState LOG = Blocks.OAK_LOG.getDefaultState();
    private final BlockState LEAVES;

    public LargeShrubFeature(BlockState leaves, Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
        super(function_1, false);

        LEAVES = leaves;
    }

    public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld world, Random rand, BlockPos blockPos_1) {
        int height = 3;
        blockPos_1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, blockPos_1);

		/*if ((canTreeReplace(world, blockPos_1) || world.testBlockState(blockPos_1, (blockState_1) -> {
			Block block = blockState_1.getBlock();
			return block == Blocks.GRASS;
		})) && world.testBlockState(blockPos_1, (blockState_1) -> {
			Block block = blockState_1.getBlock();
			return block != Blocks.GRASS_BLOCK && block != Blocks.DIRT && block != Blocks.COARSE_DIRT;
		}))*/

        if (blockPos_1.getY() >= 1 && blockPos_1.getY() + height + 1 <= 256 && isNaturalDirtOrGrass(world, blockPos_1.down())) {
            setBlockState(set_1, world, blockPos_1.down(), Blocks.DIRT.getDefaultState());

            for (int i = -2; i <= 2; ++i) {
                for (int j = -2; j <= 2; ++j) {
                    setBlockState(set_1, world, blockPos_1.add(i, 0, j), LEAVES);
                }
            }
            setBlockState(set_1, world, blockPos_1.add(0, 1, 1), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(0, 1, -1), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(1, 1, 0), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(0, 1, 0), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(-1, 1, 0), LEAVES);

            //counters
            int c0 = 3;
            int c1 = 0;

            for (int n = 4; n > 0; --n) {
                if (c1 == 2) break;

                if (rand.nextInt(c0) == 0) {
                    setBlockState(set_1, world, blockPos_1.add(coords[n - 1][0], 1, coords[n - 1][1]), LEAVES);
                    ++c1;
                } else if (c0 > 1) --c0;
            }

            setBlockState(set_1, world, blockPos_1, LOG);

            return true;
        } else {
            return false;
        }
    }

}