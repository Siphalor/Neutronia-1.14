package team.abnormals.neutronia.blocks.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.abnormals.neutronia.world.gen.feature.SavannaTreeFeature;

import java.util.Random;

public class PalmSaplingGenerator extends SaplingGenerator {

    @Override
    protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random var1) {
        return new SavannaTreeFeature(DefaultFeatureConfig::deserialize, true);
    }

}