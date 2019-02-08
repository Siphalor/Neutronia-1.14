package team.abnormals.neutronia.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import team.abnormals.neutronia.init.NFeatures;
import team.abnormals.neutronia.world.gen.features.village.PillagerVillageData;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class PillagerOutpostFeature extends AbstractTempleFeature<PillagerOutpostFeatureConfig> {
   private static final List<Biome.SpawnEntry> field_16656;

   public PillagerOutpostFeature(Function<Dynamic<?>, ? extends PillagerOutpostFeatureConfig> function_1) {
      super(function_1);
   }

   public String getName() {
      return "Pillager_Outpost_Test";
   }

   public int method_14021() {
      return 3;
   }

   public List<Biome.SpawnEntry> getMonsterSpawns() {
      return field_16656;
   }

   public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator_1, Random random_1, int int_1, int int_2) {
      ChunkPos chunkPos_1 = this.method_14018(chunkGenerator_1, random_1, int_1, int_2, 0, 0);
      if (int_1 == chunkPos_1.x && int_2 == chunkPos_1.z) {
         int int_3 = int_1 >> 4;
         int int_4 = int_2 >> 4;
         random_1.setSeed((long)(int_3 ^ int_4 << 4) ^ chunkGenerator_1.getSeed());
         random_1.nextInt();
         if (random_1.nextInt(5) != 0) {
            return false;
         } else {
            Biome biome_1 = chunkGenerator_1.getBiomeSource().getBiome(new BlockPos((int_1 << 4) + 9, 0, (int_2 << 4) + 9));
            return chunkGenerator_1.hasStructure(biome_1, NFeatures.PILLAGER_MANSION);
         }
      } else {
         return false;
      }
   }

   public StructureFeature.StructureStartFactory getStructureStartFactory() {
      return PillagerOutpostFeature.class_3771::new;
   }

   protected int method_13774() {
      return 165745296;
   }

   static {
      field_16656 = Lists.newArrayList(new Biome.SpawnEntry(EntityType.PILLAGER, 1, 1, 1));
   }

   public static class class_3771 extends StructureStart {
      public class_3771(StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1) {
         super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
      }

      public void initialize(ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1, int int_1, int int_2, Biome biome_1) {
         BlockPos blockPos_1 = new BlockPos(int_1 * 16, 90, int_2 * 16);
         PillagerVillageData.method_16650(chunkGenerator_1, structureManager_1, blockPos_1, this.children, this.random);
         this.setBoundingBoxFromChildren();
      }
   }
}
