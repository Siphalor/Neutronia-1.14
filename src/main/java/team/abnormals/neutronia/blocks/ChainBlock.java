package team.abnormals.neutronia.blocks;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ChainBlock extends BaseModBlock implements INeutroniaBlock {

    public ChainBlock() {
        super(Material.METAL, "chain");
    }

    public ChainBlock(String name) {
        super(Material.METAL, name + "_chain");
    }

    public ChainBlock(Material material, String name) {
        super(material, name);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1) {
        return VoxelShapes.empty();
    }

    @Override
    public Vec3d getOffsetPos(BlockState state, BlockView worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos.down()).getBlock() instanceof LanternBlock || worldIn.getBlockState(pos.down()).getBlock() instanceof RedstoneLanternBlock) {
            return worldIn.getBlockState(pos.down()).getBlock().getOffsetPos(state, worldIn, pos);
        }
        return new Vec3d(0D, 0D, 0D);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.MIPPED_CUTOUT;
    }

}