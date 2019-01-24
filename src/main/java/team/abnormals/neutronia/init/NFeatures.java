package team.abnormals.neutronia.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import team.abnormals.neutronia.world.gen.features.PillagerOutpostFeature;
import team.abnormals.neutronia.world.gen.features.PillagerOutpostFeatureConfig;

public class NFeatures {

    public static final StructureFeature<PillagerOutpostFeatureConfig> PILLAGER_OUTPOST = register("pillager_outpost", new PillagerOutpostFeature(PillagerOutpostFeatureConfig::deserialize));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
        return Registry.register(Registry.FEATURE, string_1, feature_1);
    }

}
