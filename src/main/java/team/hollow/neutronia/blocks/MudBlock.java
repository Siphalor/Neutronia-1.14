package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class MudBlock extends FallingBlock {

    protected static final VoxelShape SOUL_SAND_AABB = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public MudBlock() {
        super(FabricBlockSettings.of(Material.ORGANIC).hardness(0.5F).sounds(BlockSoundGroup.GRAVEL), "mud");
    }

    public MudBlock(String name) {
        super(FabricBlockSettings.of(Material.ORGANIC).hardness(0.5F).sounds(BlockSoundGroup.GRAVEL), name);
    }

    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext) {
        return SOUL_SAND_AABB;
    }

    public boolean hasSolidTopSurface(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        entity_1.setVelocity(entity_1.getVelocity().subtract(0.4D, 1.0D, 0.4D));
    }

    public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        BubbleColumnBlock.update(world_1, blockPos_1.up(), false);
    }

    public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_2) {
        world_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(world_1));
    }

    public int getTickRate(ViewableWorld viewableWorld_1) {
        return 20;
    }

    public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2) {
        world_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(world_1));
    }

    public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, BlockPlacementEnvironment blockPlacementEnvironment_1) {
        return false;
    }

}