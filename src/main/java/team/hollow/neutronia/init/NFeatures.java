package team.hollow.neutronia.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionFeature;
import team.hollow.neutronia.world.gen.features.pillager_mansion.PillagerMansionFeatureConfig;

public class NFeatures {

    public static final StructureFeature<PillagerMansionFeatureConfig> PILLAGER_MANSION = register("pillager_mansion", new PillagerMansionFeature());
    public static final StructureFeature<PillagerMansionFeatureConfig> CARTOGRAPHER_CAMP = register("cartographer_camp", new PillagerMansionFeature());

    static {
        Feature.STRUCTURES.put("Pillager_Mansion", PILLAGER_MANSION);
        Feature.STRUCTURES.put("Cartographer_Camp", CARTOGRAPHER_CAMP);
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
        return Registry.register(Registry.FEATURE, string_1, feature_1);
    }

}
