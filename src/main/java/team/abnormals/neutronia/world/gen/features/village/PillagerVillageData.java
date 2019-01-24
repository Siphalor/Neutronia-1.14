package team.abnormals.neutronia.world.gen.features.village;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sortme.StructurePiece;
import net.minecraft.sortme.structures.StructureManager;
import net.minecraft.sortme.structures.processor.BlockRotStructureProcessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.List;

public class PillagerVillageData {
   public static void method_16650(ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1, BlockPos blockPos_1, List<class_3443> list_1, ChunkRandom chunkRandom_1) {
      class_3778.method_16605(new Identifier("pillager_outpost/base_plates"), 7, PillagerVillageData.class_3792::new, chunkGenerator_1, structureManager_1, blockPos_1, list_1, chunkRandom_1);
   }

   static {
      class_3778.field_16666.method_16640(new class_3785(new Identifier("pillager_outpost/base_plates"), new Identifier("empty"), ImmutableList.of(Pair.of(new class_3781("pillager_outpost/base_plate"), 1)), class_3785.Projection.RIGID));
      class_3778.field_16666.method_16640(new class_3785(new Identifier("pillager_outpost/towers"), new Identifier("empty"), ImmutableList.of(Pair.of(new class_3782(ImmutableList.of(new class_3781("pillager_outpost/watchtower"), new class_3781("pillager_outpost/watchtower_overgrown", ImmutableList.of(new BlockRotStructureProcessor(0.05F))))), 1)), class_3785.Projection.RIGID));
      class_3778.field_16666.method_16640(new class_3785(new Identifier("pillager_outpost/feature_plates"), new Identifier("empty"), ImmutableList.of(Pair.of(new class_3781("pillager_outpost/feature_plate"), 1)), class_3785.Projection.TERRAIN_MATCHING));
      class_3778.field_16666.method_16640(new class_3785(new Identifier("pillager_outpost/features"), new Identifier("empty"), ImmutableList.of(Pair.of(new class_3781("pillager_outpost/feature_cage1"), 1), Pair.of(new class_3781("pillager_outpost/feature_cage2"), 1), Pair.of(new class_3781("pillager_outpost/feature_logs"), 1), Pair.of(new class_3781("pillager_outpost/feature_tent1"), 1), Pair.of(new class_3781("pillager_outpost/feature_tent2"), 1), Pair.of(new class_3781("pillager_outpost/feature_targets"), 1), Pair.of(class_3777.field_16663, 6)), class_3785.Projection.RIGID));
   }

   public static class class_3792 extends class_3790 {
      public class_3792(StructureManager structureManager_1, class_3784 class_3784_1, BlockPos blockPos_1, int int_1, Rotation rotation_1) {
         super(StructurePiece.PCP, structureManager_1, class_3784_1, blockPos_1, int_1, rotation_1);
      }

      public class_3792(StructureManager structureManager_1, CompoundTag compoundTag_1) {
         super(structureManager_1, compoundTag_1, StructurePiece.PCP);
      }
   }
}
