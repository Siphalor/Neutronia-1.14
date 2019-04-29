package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.RedwoodFeature;
import team.hollow.neutronia.world.gen.feature.SmallRedwoodFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodSaplingGenerator extends LargeTreeSaplingGenerator {

   @Nullable
   protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random random_1) {
      return new SmallRedwoodFeature(DefaultFeatureConfig::deserialize);
   }

   @Nullable
   protected AbstractTreeFeature<DefaultFeatureConfig> createLargeTreeFeature(Random random_1) {
      return new RedwoodFeature(DefaultFeatureConfig::deserialize);
   }
}
