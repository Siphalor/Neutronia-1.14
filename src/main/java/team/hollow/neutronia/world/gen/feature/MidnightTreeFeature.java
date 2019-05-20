package team.hollow.neutronia.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

public abstract class MidnightTreeFeature extends MidnightNaturalFeature {
    protected final BlockState log;
    protected final BlockState leaves;

    protected MidnightTreeFeature(BlockState log, BlockState leaves) {
        this.log = log;
        if (leaves.getBlock() instanceof LeavesBlock) {
            leaves = leaves.with(LeavesBlock.PERSISTENT, false);
        }
        this.leaves = leaves;
    }

    protected boolean canFit(IWorld world, BlockPos pos, int height, IntFunction<Integer> widthSupplier) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable(pos);

        for (int localY = 0; localY < height; localY++) {
            int width = widthSupplier.apply(localY);
            for (int localZ = -width; localZ <= width; localZ++) {
                for (int localX = -width; localX <= width; localX++) {
                    mutablePos.set(pos.getX() + localX, pos.getY() + localY, pos.getZ() + localZ);
                    if (!this.canReplace(world, mutablePos)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    protected boolean canGrow(IWorld world, BlockPos pos) {
        BlockPos groundPos = pos.down();
        BlockState groundState = world.getBlockState(groundPos);
        Block groundBlock = groundState.getBlock();
        return groundBlock.canPlaceAt(groundState, world, groundPos);
    }

    protected void notifyGrowth(IWorld world, BlockPos pos) {
        BlockPos groundPos = pos.down();
        BlockState groundState = world.getBlockState(groundPos);
        Block groundBlock = groundState.getBlock();
//        groundBlock.onPlantGrow(groundState, world, groundPos, pos);
    }

    protected void placeLog(World world, BlockPos pos) {
        this.placeState(world, pos, this.log);
    }

    protected void placeLog(World world, BlockPos pos, Direction.Axis axis) {
        this.placeState(world, pos, this.log.with(LogBlock.AXIS, axis));
    }

    protected void placeLeaves(World world, BlockPos pos) {
        this.placeState(world, pos, this.leaves);
    }

    protected Set<BlockPos> produceBlob(BlockPos origin, double radius) {
        return this.produceBlob(origin, radius, radius);
    }

    protected Set<BlockPos> produceBlob(BlockPos origin, double horizontalRadius, double verticalRadius) {
        Set<BlockPos> positions = new HashSet<>();

        int verticalRadiusCeil = MathHelper.ceil(verticalRadius);
        int horizontalRadiusCeil = MathHelper.ceil(horizontalRadius);

        BlockPos minPos = origin.add(-horizontalRadiusCeil, -verticalRadiusCeil, -horizontalRadiusCeil);
        BlockPos maxPos = origin.add(horizontalRadiusCeil, verticalRadiusCeil, horizontalRadiusCeil);
        for (BlockPos pos : BlockPos.iterate(minPos, maxPos)) {
            double deltaX = (pos.getX() - origin.getX()) / horizontalRadius;
            double deltaY = (pos.getY() - origin.getY()) / verticalRadius;
            double deltaZ = (pos.getZ() - origin.getZ()) / horizontalRadius;
            double distanceSquared = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
            if (distanceSquared <= 1.0) {
                positions.add(pos.toImmutable());
            }
        }

        return positions;
    }

    @Override
    public GenerationStep.Feature getEventType() {
        return GenerationStep.Feature.VEGETAL_DECORATION;
    }
}