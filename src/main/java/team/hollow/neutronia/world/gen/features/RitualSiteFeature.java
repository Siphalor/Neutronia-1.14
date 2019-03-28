package team.hollow.neutronia.world.gen.features;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.world.gen.RitualSiteGenerator;
import team.hollow.test.ExampleMod;

import java.util.Random;
import java.util.function.Function;

public class RitualSiteFeature extends StructureFeature<RitualSiteFeatureConfig> {

    public RitualSiteFeature(Function<Dynamic<?>, ? extends RitualSiteFeatureConfig> fn) {
        super(fn);
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return Start::new;
    }

    @Override
    public String getName() {
        return "neutronia:ritual_site";
    }

    @Override
    public int getRadius() {
        return 5;
    }

    protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator_1, Random random_1, int int_1, int int_2, int int_3, int int_4) {
        int distance = 4;
        int separation = 2;
        int int_7 = int_1 + distance * int_3;
        int int_8 = int_2 + distance * int_4;
        int int_9 = int_7 < 0 ? int_7 - distance + 1 : int_7;
        int int_10 = int_8 < 0 ? int_8 - distance + 1 : int_8;
        int int_11 = int_9 / distance;
        int int_12 = int_10 / distance;
        ((ChunkRandom) random_1).setStructureSeed(chunkGenerator_1.getSeed(), int_11, int_12, 10387312);
        int_11 *= distance;
        int_12 *= distance;
        int_11 += random_1.nextInt(distance - separation);
        int_12 += random_1.nextInt(distance - separation);
        return new ChunkPos(int_11, int_12);
    }

    public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator_1, Random random_1, int int_1, int int_2) {
        ChunkPos chunkPos_1 = this.getStart(chunkGenerator_1, random_1, int_1, int_2, 0, 0);
        if (int_1 == chunkPos_1.x && int_2 == chunkPos_1.z) {
            Biome biome_1 = chunkGenerator_1.getBiomeSource().getBiome(new BlockPos((int_1 << 4) + 9, 0, (int_2 << 4) + 9));
            return chunkGenerator_1.hasStructure(biome_1, ExampleMod.RITUAL_SITE_FEATURE);
        } else {
            return false;
        }
    }

    public static class Start extends StructureStart {

        public Start(StructureFeature<?> feature, int chunkX, int chunkZ, Biome biome, MutableIntBoundingBox boundingBox, int references, long seed) {
            super(feature, chunkX, chunkZ, biome, boundingBox, references, seed);
        }

        @Override
        public void initialize(ChunkGenerator<?> cg, StructureManager manager, int chunkX, int chunkZ, Biome biome) {
            BlockPos pos = new BlockPos(chunkX << 4, 90, chunkZ << 4);
            RitualSiteGenerator.addPieces(cg, manager, pos, this.children, this.random);
            this.setBoundingBoxFromChildren();
        }

    }

}