package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlockMud extends BlockFalling {

    protected static final VoxelShape SOUL_SAND_AABB = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    public BlockMud() {
        super(FabricBlockSettings.of(Material.ORGANIC).hardness(0.5F).sounds(BlockSoundGroup.GRAVEL).build(), "mud");
    }

    public BlockMud(String name) {
        super(FabricBlockSettings.of(Material.ORGANIC).hardness(0.5F).sounds(BlockSoundGroup.GRAVEL).build(), name);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return SOUL_SAND_AABB;
    }

    public boolean hasSolidTopSurface(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }

    public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
        entity_1.velocityX *= 0.4D;
        entity_1.velocityZ *= 0.4D;
    }

}