package team.hollow.neutronia.blocks.vanillish;

import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import team.hollow.neutronia.api.Climbable;

public class NeutroniaTrapdoorBlock extends TrapdoorBlock implements Climbable {

    public NeutroniaTrapdoorBlock(Settings settings) {
        super(settings);
    }

    /**
     * Determines if the passed LivingEntity can climb this block.
     *
     * @param entity The LivingEntity that is attempting to climb this block.
     * @param state  The block state of the ladder being climbed.
     * @param pos    The position of the block.
     */
    @Override
    public boolean canClimb(LivingEntity entity, BlockState state, BlockPos pos) {
        return true;
    }
}
