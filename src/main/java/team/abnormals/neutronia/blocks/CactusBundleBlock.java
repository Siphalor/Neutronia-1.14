package team.abnormals.neutronia.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.abnormals.neutronia.init.NDamageSources;

public class CactusBundleBlock extends NeutroniaPillarBlock {

    public CactusBundleBlock() {
        super(Material.CACTUS, "cactus_bundle");
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    public void onEntityCollision(BlockState blockState, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.damage(NDamageSources.CACTUS_BUNDLE, 1.0F * entityIn.fallDistance);
    }

}