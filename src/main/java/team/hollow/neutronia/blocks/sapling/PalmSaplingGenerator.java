package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.PalmTreeFeature;

import java.util.Random;

public class PalmSaplingGenerator extends SaplingGenerator {

    private BlockState log, leaves;

    public PalmSaplingGenerator(BlockState log, BlockState leaves) {
        this.log = log;
        this.leaves = leaves;
    }

    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random var1) {
        return new PalmTreeFeature(log, leaves, DefaultFeatureConfig::deserialize);
    }

}