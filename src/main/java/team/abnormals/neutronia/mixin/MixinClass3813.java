package team.abnormals.neutronia.mixin;

import net.minecraft.structure.generator.village.VillageGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillageGenerator.class)
public class MixinClass3813 {

    @Inject(method = "addPieces(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;Lnet/minecraft/util/math/BlockPos;Ljava/util/List;Lnet/minecraft/world/gen/ChunkRandom;Lnet/minecraft/world/gen/feature/NewVillageFeatureConfig;)V", at = @At("RETURN"))
    private static void registerVillages(CallbackInfo info) {

    }

}
