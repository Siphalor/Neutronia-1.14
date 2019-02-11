package team.abnormals.neutronia.world.gen.features;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static team.abnormals.neutronia.init.NStructureFeatures.*;

public class OreGeneration {
    public static void registerOres() {
        for (Biome biome : Registry.BIOME) {
            biome.addStructureFeature(PILLAGER_MANSION, new PillagerOutpostFeatureConfig(0.4D));
            biome.addStructureFeature(CARTOGRAPHER_CAMP, new PillagerOutpostFeatureConfig(0.4D));
        }
    }
}