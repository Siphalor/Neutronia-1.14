package team.hollow.neutronia.world.gen.features.totem_poles;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.ExampleMod;

import java.util.Random;

//To summarize this class declares what a totempole is and what rules apply to it
public class TotemPoleFeature extends StructureFeature<DefaultFeatureConfig> {

    public TotemPoleFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    public String getName() {
        return "neutronia:totem_poles";
    }

    public int getRadius() {
        return 3;
    }

    public StructureStartFactory getStructureStartFactory() {
        return Start::new;
    }

    @Override
    public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator, Random random, int int_1, int int_2) {
        return true;
    }

    public static class Start extends StructureStart {
        public Start(StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1) {
            super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
        }

        //This plans out the totem pole and tells the genorator to getModMenuBadge it
        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome) {
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, ExampleMod.TOTEM_POLE_FEATURE);
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos startingPos = new BlockPos(x, 0, z);
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            TotemPoleGenerator.addParts(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();
        }
    }
}
