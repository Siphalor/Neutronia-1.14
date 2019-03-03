package team.hollow.neutronia.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import team.hollow.neutronia.blocks.NeutroniaBushBlock;

import java.util.Random;

public class FungiFeature extends MidnightAbstractFeature<DefaultFeatureConfig> {
    private static final BlockState[] FUNGI_STATES = new BlockState[]{
            Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState(),
            Blocks.RED_MUSHROOM_BLOCK.getDefaultState()
    };

    public FungiFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    public boolean placeFeature(IWorld world, Random rand, BlockPos origin) {
        BlockState state = FUNGI_STATES[rand.nextInt(FUNGI_STATES.length)];
        if (((NeutroniaBushBlock) state.getBlock()).canPlaceAt(state, world, origin)) {
            this.setBlockState(world, origin, state);
            return true;
        }
        return false;
    }

    @Override
    public GenerationStep.Feature getEventType() {
        return GenerationStep.Feature.VEGETAL_DECORATION;
    }
}