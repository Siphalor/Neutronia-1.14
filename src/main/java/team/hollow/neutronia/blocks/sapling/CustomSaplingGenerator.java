package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class CustomSaplingGenerator extends SaplingGenerator {

    public Block log, leaves;
    public AbstractTreeFeature treeFeature;

    public CustomSaplingGenerator(Block log, Block leaves, AbstractTreeFeature treeFeature) {
        this.log = log;
        this.leaves = leaves;
        this.treeFeature = treeFeature;
    }

    @Nullable
    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random var1) {
        return treeFeature;
    }

}
