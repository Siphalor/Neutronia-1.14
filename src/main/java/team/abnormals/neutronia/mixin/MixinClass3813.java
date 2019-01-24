package team.abnormals.neutronia.mixin;

import net.minecraft.class_3813;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3813.class)
public class MixinClass3813 {

    @Inject(method = "method_16753(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/sortme/structures/StructureManager;Lnet/minecraft/util/math/BlockPos;Ljava/util/List;Lnet/minecraft/world/gen/ChunkRandom;Lnet/minecraft/world/gen/feature/NewVillageFeatureConfig;)V", at = @At("RETURN"))
    private static void registerVillages(CallbackInfo info) {

    }

}
