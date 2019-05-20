package team.hollow.neutronia.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;

public class CrystalClusterFeature extends MidnightAbstractFeature<DefaultFeatureConfig> {
    private final int radius;
    private final int maxHeight;

    private final BlockState rock;
    private final BlockState crystal;

    public CrystalClusterFeature(int radius, int maxHeight, BlockState rock, BlockState crystal) {
        super(DefaultFeatureConfig::deserialize);
        this.radius = radius;
        this.maxHeight = maxHeight;
        this.rock = rock;
        this.crystal = crystal;
    }

    @Override
    public boolean placeFeature(IWorld world, Random rand, BlockPos origin) {
        int size = (this.radius * 2) + 1;

        int[] heights = new int[size * size];
        BlockPos basePos = this.populateHeights(world, rand, origin, heights, size);

        if (basePos == null || !this.canGenerate(world, origin, heights, size)) {
            return false;
        }

        BlockPos.Mutable mutablePos = new BlockPos.Mutable();
        for (int localZ = -this.radius; localZ <= this.radius; localZ++) {
            for (int localX = -this.radius; localX <= this.radius; localX++) {
                int height = heights[(localX + this.radius) + (localZ + this.radius) * size];
                if (height > 0) {
                    mutablePos.set(basePos.getX() + localX, basePos.getY(), basePos.getZ() + localZ);
                    this.generatePillar(world, rand, mutablePos, height);
                }
            }
        }

        return true;
    }

    private BlockPos populateHeights(IWorld world, Random rand, BlockPos origin, int[] heights, int size) {
        BlockPos.Mutable basePos = new BlockPos.Mutable(origin);

        for (int localZ = -this.radius; localZ <= this.radius; localZ++) {
            for (int localX = -this.radius; localX <= this.radius; localX++) {
                int index = (localX + this.radius) + (localZ + this.radius) * size;

                double deltaX = localX + rand.nextDouble() * 2.0 - 1.0;
                double deltaZ = localZ + rand.nextDouble() * 2.0 - 1.0;
                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                double alpha = (this.radius - distance) / this.radius;

                int height = MathHelper.floor(alpha * this.maxHeight);
                if (height > 0) {
                    BlockPos surfacePos = this.findSurfaceBelow(world, origin.add(localX, 0, localZ), 16);
                    if (surfacePos == null) {
                        return null;
                    }

                    if (surfacePos.getY() < basePos.getY()) {
                        basePos.setY(surfacePos.getY());
                    }

                    heights[index] = height;
                }
            }
        }

        return basePos.toImmutable();
    }

    private boolean canGenerate(IWorld world, BlockPos origin, int[] heights, int size) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable(origin);
        int centerHeight = heights[this.radius + this.radius * size] + 1;
        for (int localY = 0; localY < centerHeight; localY++) {
            mutablePos.setY(origin.getY() + localY);
            if (!world.isAir(mutablePos)) {
                return false;
            }
        }
        return true;
    }

    private void generatePillar(IWorld world, Random rand, BlockPos.Mutable mutablePos, int height) {
        int originY = mutablePos.getY();
        for (int offsetY = 0; offsetY < height; offsetY++) {
            mutablePos.setY(originY + offsetY);
            this.trySetBlock(world, mutablePos, this.rock);
        }
        if (rand.nextInt(2) == 0) {
            mutablePos.setY(originY + height);
            this.trySetBlock(world, mutablePos, this.crystal);
        }
    }

    private BlockPos findSurfaceBelow(IWorld world, BlockPos origin, int maxSteps) {
        BlockState currentState = world.getBlockState(origin);
        BlockPos.Mutable currentPos = new BlockPos.Mutable(origin);
        for (int i = 0; i < maxSteps; i++) {
            currentPos.setOffset(Direction.DOWN);
            BlockState nextState = world.getBlockState(currentPos);
            if (currentState.getBlock() == Blocks.AIR && nextState.isAir()) {
                currentPos.setOffset(Direction.UP);
                return currentPos.toImmutable();
            }
            currentState = nextState;
        }
        return null;
    }

    private void trySetBlock(IWorld world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, 3);
    }
}