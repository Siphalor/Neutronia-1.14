package team.abnormals.neutronia.entity.ai.goal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

public class FindDiamondBlockGoal extends MoveToTargetPosGoal {
    private final VillagerPlusEntity owner;

    public FindDiamondBlockGoal(VillagerPlusEntity entity, double double_1) {
        super(entity, double_1, 16);
        owner = entity;
    }

    @Override
    public boolean canStart() {
        return true;
    }

    public boolean shouldContinue() {
        return true;
    }

    public void tick() {
        super.tick();
        this.owner.getLookControl().lookAt((double)this.targetPos.getX() + 0.5D, (double)(this.targetPos.getY() + 1), (double)this.targetPos.getZ() + 0.5D, 10.0F, (float)this.owner.method_5978());
        if (this.hasReached()) {
            World iWorld_1 = this.owner.world;
            BlockPos blockPos_1 = this.targetPos.down();
            BlockState blockState_1 = iWorld_1.getBlockState(blockPos_1);
            Block block_1 = blockState_1.getBlock();
            if (block_1 == Blocks.OAK_PLANKS || block_1 == Blocks.ACACIA_PLANKS) {
                iWorld_1.setBlockState(blockPos_1, Blocks.DIAMOND_BLOCK.getDefaultState(), 2);
            }
        }

    }

    @Override
    protected boolean isTargetPos(ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        Block block_1 = viewableWorld_1.getBlockState(blockPos_1).getBlock();
        return block_1 == Blocks.OAK_PLANKS || block_1 == Blocks.ACACIA_PLANKS;
    }

}
