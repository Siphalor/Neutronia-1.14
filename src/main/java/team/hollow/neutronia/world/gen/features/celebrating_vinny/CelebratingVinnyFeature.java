package team.hollow.neutronia.world.gen.features.celebrating_vinny;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.test.ExampleMod;

import java.util.Random;

public class CelebratingVinnyFeature extends StructureFeature<DefaultFeatureConfig> {

    public CelebratingVinnyFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    public String getName() {
        return "neutronia:celebrating_vinny";
    }

    public int getRadius() {
        return 3;
    }

    public StructureStartFactory getStructureStartFactory() {
        return Start::new;
    }

    protected int getSeedModifier() {
        return 0;
    }

    @Override
    public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator, Random random, int int_1, int int_2) {
        return true;
    }

    public static class Start extends StructureStart {
        public Start(StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1) {
            super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
        }

        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome) {
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, ExampleMod.TOTEM_POLE_FEATURE);
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos startingPos = new BlockPos(x, 0, z);
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            CelebratingVinnyGenerator.addParts(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();
        }
    }
}
