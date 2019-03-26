package team.hollow.neutronia.mixin.events.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.chunk.ChunkCache;

@Mixin(ChunkCache.class)
public interface ChunkCacheEmptyMixin {
	@Accessor public boolean getEmpty();
}
