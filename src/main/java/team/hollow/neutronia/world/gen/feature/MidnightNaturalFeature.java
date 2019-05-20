package team.hollow.neutronia.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public abstract class MidnightNaturalFeature extends MidnightAbstractFeature<DefaultFeatureConfig> {

    public MidnightNaturalFeature() {
        super(DefaultFeatureConfig::deserialize);
    }

    protected void placeState(World world, BlockPos pos, BlockState state) {
        if (this.canReplace(world, pos)) {
            world.setBlockState(pos, state, 2 | 16);
        }
    }

    protected boolean canReplace(IWorld world, BlockPos pos) {
        if (world.isAir(pos)) {
            return false;
        }

        BlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state) || state.getBlock().matches(BlockTags.LEAVES) || state.getMaterial() == Material.REPLACEABLE_PLANT;
    }
}