package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.PalmTreeFeature;

import java.util.Random;

public class MangroveSaplingGenerator extends SaplingGenerator {

    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random var1) {
        return new PalmTreeFeature(DefaultFeatureConfig::deserialize, true);
    }

}