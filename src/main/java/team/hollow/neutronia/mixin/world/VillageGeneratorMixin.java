package team.hollow.neutronia.mixin.world;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.generator.village.VillageGenerator;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.VillageFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.world.gen.features.SwampVillageData;

import java.util.List;

@Mixin(VillageGenerator.class)
public class VillageGeneratorMixin {

    @Inject(method = "addPieces(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;Lnet/minecraft/util/math/BlockPos;Ljava/util/List;Lnet/minecraft/world/gen/ChunkRandom;Lnet/minecraft/world/gen/feature/VillageFeatureConfig;)V", at = @At(value = "RETURN"))
    private static void addPieces(ChunkGenerator<?> chunkGenerator_1, StructureManager structureManager_1, BlockPos blockPos_1, List<StructurePiece> list_1, ChunkRandom chunkRandom_1, VillageFeatureConfig villageFeatureConfig_1, CallbackInfo info) {
        SwampVillageData.initialize();
        StructurePoolBasedGenerator.addPieces(villageFeatureConfig_1.startPool, villageFeatureConfig_1.size, VillageGenerator.Piece::new, chunkGenerator_1, structureManager_1, blockPos_1, list_1, chunkRandom_1);
    }

}