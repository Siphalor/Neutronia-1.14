package team.abnormals.neutronia.world.gen.features;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.BlockRotStructureProcessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import team.abnormals.neutronia.Neutronia;

import java.util.List;

public class PillagerOutpostGenerator {
    static {
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(Neutronia.MOD_ID, "pillager_outpost/base_plates"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("neutronia:pillager_outpost/base_plate"), 1)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(Neutronia.MOD_ID, "pillager_outpost/towers"), new Identifier("empty"), ImmutableList.of(Pair.of(new ListPoolElement(ImmutableList.of(new SinglePoolElement("neutronia:pillager_outpost/watchtower"), new SinglePoolElement("neutronia:pillager_outpost/watchtower_overgrown", ImmutableList.of(new BlockRotStructureProcessor(0.05F))))), 1)), StructurePool.Projection.RIGID));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(Neutronia.MOD_ID, "pillager_outpost/feature_plates"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_plate"), 1)), StructurePool.Projection.TERRAIN_MATCHING));
        StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(Neutronia.MOD_ID, "pillager_outpost/features"), new Identifier("empty"), ImmutableList.of(Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_cage1"), 1), Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_cage2"), 1), Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_logs"), 1), Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_tent1"), 1), Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_tent2"), 1), Pair.of(new SinglePoolElement("neutronia:pillager_outpost/feature_targets"), 1), Pair.of(EmptyPoolElement.INSTANCE, 6)), StructurePool.Projection.RIGID));
    }

    public static void addPieces(ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1, BlockPos blockPos_1, List<StructurePiece> list_1, ChunkRandom chunkRandom_1) {
        StructurePoolBasedGenerator.addPieces(new Identifier("pillager_outpost/base_plates"), 7, PillagerOutpostGenerator.Piece::new, chunkGenerator_1, structureManager_1, blockPos_1, list_1, chunkRandom_1);
    }

    public static class Piece extends PoolStructurePiece {
        public Piece(StructureManager structureManager_1, StructurePoolElement structurePoolElement_1, BlockPos blockPos_1, int int_1, Rotation rotation_1) {
            super(StructurePieceType.PILLAGER_OUTPOST, structureManager_1, structurePoolElement_1, blockPos_1, int_1, rotation_1);
        }

        public Piece(StructureManager structureManager_1, CompoundTag compoundTag_1) {
            super(structureManager_1, compoundTag_1, StructurePieceType.PILLAGER_OUTPOST);
        }
    }
}