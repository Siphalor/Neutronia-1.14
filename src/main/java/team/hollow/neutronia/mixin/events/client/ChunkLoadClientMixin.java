package team.hollow.neutronia.mixin.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.hollow.neutronia.event_system.EventCore;

@Mixin(WorldChunk.class)
@Environment(EnvType.CLIENT)
public abstract class ChunkLoadClientMixin {
    @Inject(at = @At("RETURN"), method = "loadFromPacket")
    private void onLoadFromPacket(PacketByteBuf packetByteBuf_1, CompoundTag compoundTag_1, int int_1, boolean boolean_1, CallbackInfo info) {
        WorldChunk _this = (WorldChunk) (Object) this;
        EventCore.instance.onChunkLoad(true, _this);
    }
}