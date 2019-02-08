package team.abnormals.neutronia.world.gen.features;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import team.abnormals.neutronia.init.NFeatures;

public class OreGeneration {
    public static void registerOres() {
        for (Biome biome : Registry.BIOME) {
            biome.addStructureFeature(NFeatures.PILLAGER_MANSION, new PillagerOutpostFeatureConfig(0.4D));
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Registry.BLOCK.get(new Identifier("cotton:copper_ore")).getDefaultState(), 17), Decorator.COUNT_RANGE, new RangeDecoratorConfig(20, 0, 0, 128)));
        }
    }
}