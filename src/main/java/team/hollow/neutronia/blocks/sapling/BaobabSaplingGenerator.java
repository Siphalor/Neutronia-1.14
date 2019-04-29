package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.PalmTreeFeature;

import java.util.Random;

public class BaobabSaplingGenerator extends SaplingGenerator {

    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random var1) {
        return new PalmTreeFeature(DefaultFeatureConfig::deserialize) {
            @Override
            protected BlockState getLeavesBlockState(IWorld world, BlockPos origin, BlockPos pos) {
                return Blocks.DARK_OAK_LEAVES.getDefaultState();
            }

            @Override
            protected BlockState getLogBlockState(IWorld world, BlockPos origin, BlockPos pos) {
                return Blocks.DARK_OAK_LOG.getDefaultState();
            }
        };
    }

}