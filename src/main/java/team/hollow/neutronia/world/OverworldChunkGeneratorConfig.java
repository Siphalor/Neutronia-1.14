package team.hollow.neutronia.world;

import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

public class OverworldChunkGeneratorConfig extends ChunkGeneratorConfig {
   private final int field_13224 = 4;
   private final int field_13223 = 4;
   private final int field_13222 = -1;
   private final int field_13221 = 63;

   public int getBiomeSize() {
      return 4;
   }

   public int getRiverSize() {
      return 4;
   }

   public int getForcedBiome() {
      return -1;
   }

   public int getMinY() {
      return 0;
   }
}
