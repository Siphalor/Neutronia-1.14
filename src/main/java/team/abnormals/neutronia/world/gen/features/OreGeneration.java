package team.abnormals.neutronia.world.gen.features;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import team.abnormals.neutronia.init.NStructureFeatures;

public class OreGeneration {
    public static void registerOres() {
        for (Biome biome : Registry.BIOME) {
            System.out.println("Test");
            biome.addStructureFeature(NStructureFeatures.PILLAGER_MANSION, new PillagerOutpostFeatureConfig(0.4D));
        }
    }
}