package team.hollow.neutronia.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import team.hollow.neutronia.api.Climbable;

public class CustomLadderBlock extends LadderBlock implements Climbable {

    public CustomLadderBlock() {
        super(Settings.copy(Blocks.LADDER));
        setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public boolean canClimb(LivingEntity entity, BlockState state, BlockPos pos) {
        return true;
    }

}