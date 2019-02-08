package team.abnormals.neutronia.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.particle.BlockStateParticleParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class FallingBlock extends BaseModBlock implements INeutroniaBlock {
    public static boolean fallInstantly;

    public FallingBlock(String name) {
        super(Material.SAND, name);
    }

    public FallingBlock(Material materialIn, String name) {
        super(materialIn, name);
    }

    public FallingBlock(Settings builder, String name) {
        super(builder, name);
    }

    public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2) {
        world_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(world_1));
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(iWorld_1));
        return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    public void scheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (!world_1.isClient) {
            this.tryStartFalling(world_1, blockPos_1);
        }

    }

    private void tryStartFalling(World world_1, BlockPos blockPos_1) {
        if (canFallThrough(world_1.getBlockState(blockPos_1.down())) && blockPos_1.getY() >= 0) {
            if (!world_1.isAreaLoaded(blockPos_1.add(-32, -32, -32), blockPos_1.add(32, 32, 32))) {
                if (world_1.getBlockState(blockPos_1).getBlock() == this) {
                    world_1.clearBlockState(blockPos_1);
                }

                BlockPos blockPos_2;
                for(blockPos_2 = blockPos_1.down(); canFallThrough(world_1.getBlockState(blockPos_2)) && blockPos_2.getY() > 0; blockPos_2 = blockPos_2.down()) {
                }

                if (blockPos_2.getY() > 0) {
                    world_1.setBlockState(blockPos_2.up(), this.getDefaultState());
                }
            } else if (!world_1.isClient) {
                FallingBlockEntity fallingBlockEntity_1 = new FallingBlockEntity(world_1, (double)blockPos_1.getX() + 0.5D, (double)blockPos_1.getY(), (double)blockPos_1.getZ() + 0.5D, world_1.getBlockState(blockPos_1));
                this.configureFallingBlockEntity(fallingBlockEntity_1);
                world_1.spawnEntity(fallingBlockEntity_1);
            }

        }
    }

    protected void configureFallingBlockEntity(FallingBlockEntity fallingBlockEntity_1) {
    }

    public int getTickRate(ViewableWorld viewableWorld_1) {
        return 2;
    }

    public static boolean canFallThrough(BlockState blockState_1) {
        Block block_1 = blockState_1.getBlock();
        Material material_1 = blockState_1.getMaterial();
        return blockState_1.isAir() || block_1 == Blocks.FIRE || material_1.isLiquid() || material_1.isReplaceable();
    }

    public void onLanding(World world_1, BlockPos blockPos_1, BlockState blockState_1, BlockState blockState_2) {
    }

    public void onDestroyedOnLanding(World world_1, BlockPos blockPos_1) {
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (random_1.nextInt(16) == 0) {
            BlockPos blockPos_2 = blockPos_1.down();
            if (canFallThrough(world_1.getBlockState(blockPos_2))) {
                double double_1 = (double)((float)blockPos_1.getX() + random_1.nextFloat());
                double double_2 = (double)blockPos_1.getY() - 0.05D;
                double double_3 = (double)((float)blockPos_1.getZ() + random_1.nextFloat());
                world_1.addParticle(new BlockStateParticleParameters(ParticleTypes.FALLING_DUST, blockState_1), double_1, double_2, double_3, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Environment(EnvType.CLIENT)
    public int getColor(BlockState blockState_1) {
        return -16777216;
    }

}