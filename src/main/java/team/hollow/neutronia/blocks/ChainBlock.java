package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import team.hollow.abnormalib.blocks.BaseModBlock;

public class ChainBlock extends BaseModBlock {

    public ChainBlock() {
        super(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext verticalEntityPosition_1) {
        return VoxelShapes.empty();
    }

    @Override
    public Vec3d getOffsetPos(BlockState state, BlockView worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos.down()).getBlock() instanceof LanternBlock || worldIn.getBlockState(pos.down()).getBlock() instanceof RedstoneLanternBlock)
            return worldIn.getBlockState(pos.down()).getBlock().getOffsetPos(state, worldIn, pos);
        return new Vec3d(0D, 0D, 0D);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.MIPPED_CUTOUT;
    }

}