package team.abnormals.test;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.FeatureConfig;

public class PillagerMansionFeatureConfig implements FeatureConfig {

    private final double probability;

    PillagerMansionFeatureConfig(double double_1) {
        this.probability = double_1;
    }

    static <T> PillagerMansionFeatureConfig deserialize(Dynamic<T> dynamic_1) {
        float float_1 = dynamic_1.get("probability").asFloat(1.0F);
        return new PillagerMansionFeatureConfig((double) float_1);
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> dynamicOps_1) {
        return new Dynamic<>(dynamicOps_1, dynamicOps_1.createMap(ImmutableMap.of(dynamicOps_1.createString("probability"), dynamicOps_1.createDouble(this.probability))));
    }

}