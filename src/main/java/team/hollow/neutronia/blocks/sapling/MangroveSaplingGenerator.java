package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.CherryTreeFeature;

public class MangroveSaplingGenerator extends CustomSaplingGenerator {

    public MangroveSaplingGenerator(Block log, Block leaves) {
        super(log, leaves, new CherryTreeFeature(DefaultFeatureConfig::deserialize) {
            @Override
            protected BlockState getLeavesBlockState(IWorld world, BlockPos origin, BlockPos pos) {
                return leaves.getDefaultState();
            }

            @Override
            protected BlockState getLogBlockState(IWorld world, BlockPos origin, BlockPos pos) {
                return log.getDefaultState();
            }
        });
    }

}