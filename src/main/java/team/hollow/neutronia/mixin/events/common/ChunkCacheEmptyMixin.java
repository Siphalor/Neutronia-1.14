package team.hollow.neutronia.mixin.events.common;

import net.minecraft.world.chunk.ChunkCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChunkCache.class)
public interface ChunkCacheEmptyMixin {
    @Accessor
    boolean getEmpty();
}
