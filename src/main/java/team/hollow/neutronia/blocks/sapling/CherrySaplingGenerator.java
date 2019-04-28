package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.CherryTreeFeature;

import java.util.Random;

public class CherrySaplingGenerator extends SaplingGenerator {

    private BlockState log, leaves;

    public CherrySaplingGenerator(BlockState log, BlockState leaves) {
        this.log = log;
        this.leaves = leaves;
    }

    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random var1) {
        return new CherryTreeFeature(log, leaves, DefaultFeatureConfig::deserialize);
    }

}