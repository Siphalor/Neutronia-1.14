package team.hollow.neutronia.mixin.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import team.hollow.neutronia.init.NCarvers;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    /**
     * @author OliviaTheVampire
     */
    @Overwrite
    public static void addLandCarvers(Biome biome_1) {
        biome_1.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(NCarvers.CAVE, new ProbabilityConfig(0.14285715F)));
        biome_1.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(Carver.RAVINE, new ProbabilityConfig(0.02F)));
    }

}