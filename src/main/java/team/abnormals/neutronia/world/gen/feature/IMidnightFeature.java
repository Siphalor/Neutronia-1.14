package team.abnormals.neutronia.world.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.GenerationStep;

import java.util.Random;

public interface IMidnightFeature {
    boolean placeFeature(IWorld world, Random random, BlockPos origin);

    default GenerationStep.Feature getEventType() {
        return GenerationStep.Feature.RAW_GENERATION;
    }
}