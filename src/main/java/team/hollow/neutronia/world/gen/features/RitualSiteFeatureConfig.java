package team.hollow.neutronia.world.gen.features;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.FeatureConfig;

public class RitualSiteFeatureConfig implements FeatureConfig {

    public final double probability;

    public RitualSiteFeatureConfig(double probability) {
        this.probability = probability;
    }

    public static <T> RitualSiteFeatureConfig deserialize(Dynamic<T> ops) {
        float probability = ops.get("probability").asFloat(0.0F);
        return new RitualSiteFeatureConfig(probability);
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(
                ops.createString("probability"), ops.createDouble(this.probability)
        )));
    }
}