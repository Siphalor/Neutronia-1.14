package team.hollow.neutronia.mixin.events.common;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;

@Mixin(WorldChunk.class)
@Environment(EnvType.SERVER)
public abstract class ChunkLoadServerMixin {
    @Inject(at = @At("RETURN"), method = "setLoadedToWorld")
    private void onSetLoadedToWorld(boolean boolean_1, CallbackInfo info) {
        if (boolean_1 == true) {
            WorldChunk _this = (WorldChunk) (Object) this;
            //ChunkPos p = _this.getPos();
            //System.out.println("ChunkLoadServerMixin: "+p.x+","+p.z);
            EventCore.instance.onChunkLoad(false, _this);
        }
    }
}
