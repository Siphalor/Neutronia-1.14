package team.hollow.neutronia.world.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockStatePredicate {
    boolean test(World world, BlockPos pos);
}