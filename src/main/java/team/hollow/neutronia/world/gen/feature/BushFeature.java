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

public class BushFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
    private static final BlockState LOG = Blocks.OAK_LOG.getDefaultState();
    private static final BlockState LEAVES = Blocks.OAK_LEAVES.getDefaultState();

    public BushFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
        super(function_1, false);
    }

    public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld world, Random random_1, BlockPos blockPos_1) {
        int height = 3;
        blockPos_1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, blockPos_1);

        if (blockPos_1.getY() >= 1 && blockPos_1.getY() + height + 1 <= 256 && isNaturalDirtOrGrass(world, blockPos_1.down())) {
            setBlockState(set_1, world, blockPos_1.down(), Blocks.DIRT.getDefaultState());

            setBlockState(set_1, world, blockPos_1.add(0, 0, 0), LOG);

            setBlockState(set_1, world, blockPos_1.add(1, 0, 0), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(-1, 0, 0), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(0, 0, 1), LEAVES);
            setBlockState(set_1, world, blockPos_1.add(0, 0, -1), LEAVES);

            setBlockState(set_1, world, blockPos_1.add(0, 1, 0), LEAVES);

            return true;
        } else {
            return false;
        }
    }

}