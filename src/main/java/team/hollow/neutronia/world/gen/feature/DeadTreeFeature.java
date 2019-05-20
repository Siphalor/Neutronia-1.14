package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class DeadTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
    private static final BlockState LOG = Blocks.DARK_OAK_LOG.getDefaultState();
    private static final BlockState LEAVES = Blocks.OAK_LEAVES.getDefaultState();

    public DeadTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
        super(function_1, false);
    }

    public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld world, Random random_1, BlockPos blockPos_1, MutableIntBoundingBox mutableIntBoundingBox) {
        int height = random_1.nextInt(4) + 4;
        blockPos_1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, blockPos_1);

        if (blockPos_1.getY() >= 1 && blockPos_1.getY() + height + 1 <= 256 && isNaturalDirtOrGrass(world, blockPos_1.down())) {
            //setBlockState(set_1, world, blockPos_1.down(), Blocks.DIRT.getDefaultState());

            for (int i = 0; i <= height; ++i) {
                //setBlockState(set_1, world, blockPos_1.add(0, i, 0), LOG);
                if (random_1.nextInt(3) == 0) {
                    int n1 = (random_1.nextInt(3) - 1);
                    boolean b = random_1.nextBoolean();

                    if (n1 != 0) {
                        //if (b) setBlockState(set_1, world, blockPos_1.add(n1, i, 0), LEAVES);
                        //else setBlockState(set_1, world, blockPos_1.add(0, i, n1), LEAVES);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

}