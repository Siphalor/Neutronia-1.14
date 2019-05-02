package team.hollow.neutronia.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.init.WoodRegistries;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class PalmTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {

    public PalmTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
        super(function_1, false);
    }

    public boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld world, Random random_1, BlockPos blockPos_1) {
        int height = random_1.nextInt(3) + 6;
        blockPos_1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, blockPos_1);

        if (blockPos_1.getY() >= 1 && blockPos_1.getY() + height + 1 <= 256 && !isWater(world, blockPos_1) && (this.isSandOrClay(world, blockPos_1.down()) ||
                isNaturalDirtOrGrass(world, blockPos_1.down()))) {
            setBlockState(set_1, world, blockPos_1.down(), Blocks.DIRT.getDefaultState());

            BlockPos origin = blockPos_1.add(0, height - 1, 0);

            for (int i = -1; i < 2; ++i) {
                setBlockState(set_1, world, origin.add(1, i, 0), WoodRegistries.PALM.getLeaves().getDefaultState());
                setBlockState(set_1, world, origin.add(-1, i, 0), WoodRegistries.PALM.getLeaves().getDefaultState());
                setBlockState(set_1, world, origin.add(0, i, 1), WoodRegistries.PALM.getLeaves().getDefaultState());
                setBlockState(set_1, world, origin.add(0, i, -1), WoodRegistries.PALM.getLeaves().getDefaultState());
            }

            setBlockState(set_1, world, origin.add(1, 0, 1), WoodRegistries.PALM.getLeaves().getDefaultState());
            setBlockState(set_1, world, origin.add(-1, 0, 1), WoodRegistries.PALM.getLeaves().getDefaultState());
            setBlockState(set_1, world, origin.add(1, 0, -1), WoodRegistries.PALM.getLeaves().getDefaultState());
            setBlockState(set_1, world, origin.add(-1, 0, -1), WoodRegistries.PALM.getLeaves().getDefaultState());

            for (int i = -2; i < 2; ++i) {
                if (i == 0) continue;
                setBlockState(set_1, world, origin.add(2, i, 0), WoodRegistries.PALM.getLeaves().getDefaultState());
                setBlockState(set_1, world, origin.add(-2, i, 0), WoodRegistries.PALM.getLeaves().getDefaultState());
                setBlockState(set_1, world, origin.add(0, i, 2), WoodRegistries.PALM.getLeaves().getDefaultState());
                setBlockState(set_1, world, origin.add(0, i, -2), WoodRegistries.PALM.getLeaves().getDefaultState());
            }

            setBlockState(set_1, world, origin.add(0, 1, 0), WoodRegistries.PALM.getLeaves().getDefaultState());

            for (int i = 0; i <= height - 1; ++i)
                setBlockState(set_1, world, blockPos_1.add(0, i, 0), WoodRegistries.PALM.getLog().getDefaultState());

            return true;
        } else {
            return false;
        }
    }

    private boolean isSandOrClay(TestableWorld world, BlockPos down) {
        return world.testBlockState(down, (blockState_1) -> {
            Block block_1 = blockState_1.getBlock();
            return block_1 == Blocks.RED_SAND || block_1 == Blocks.SAND || block_1 == Blocks.CLAY;
        });
    }

}