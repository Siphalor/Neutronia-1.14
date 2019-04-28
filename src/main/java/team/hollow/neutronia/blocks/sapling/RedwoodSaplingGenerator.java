package team.hollow.neutronia.blocks.sapling;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.world.gen.feature.RedwoodFeature;
import team.hollow.neutronia.world.gen.feature.SmallRedwoodFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodSaplingGenerator extends LargeTreeSaplingGenerator {

   private BlockState log, leaves;

   public RedwoodSaplingGenerator(BlockState log, BlockState leaves) {
      this.log = log;
      this.leaves = leaves;
   }

   @Nullable
   protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random random_1) {
      return new SmallRedwoodFeature(log, leaves, DefaultFeatureConfig::deserialize);
   }

   @Nullable
   protected AbstractTreeFeature<DefaultFeatureConfig> createLargeTreeFeature(Random random_1) {
      return new RedwoodFeature(log, leaves, DefaultFeatureConfig::deserialize);
   }
}
