package team.hollow.neutronia.mixin.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;

@Mixin(ClientWorld.class)
@Environment(EnvType.CLIENT)
public abstract class ChunkUnloadClientMixin {
    //method_18110 is called by unload in ClientChunkManager.
    //it adds entities in chunk to List<BlockEntity> field_18139 in world, qwhich is shit to be deleted
    @Inject(at = @At("HEAD"), method = "method_18110")
    private void onUnloadingChunkEntities(WorldChunk worldChunk_1, CallbackInfo info) {
        if (worldChunk_1 != null) {
            //ChunkPos p = worldChunk_1.getPos();
            EventCore.instance.onChunkUnload(true, worldChunk_1);
            //System.out.println("ChunkUnloadClientMixin: "+p.x+","+p.z);


            //w.ad
        }
    }
}
