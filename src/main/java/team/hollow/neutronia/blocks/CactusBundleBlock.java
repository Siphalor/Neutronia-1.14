package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hollow.neutronia.init.NDamageSources;

public class CactusBundleBlock extends NeutroniaPillarBlock {

    public CactusBundleBlock() {
        super(FabricBlockSettings.of(Material.CACTUS).sounds(BlockSoundGroup.GRASS).breakInstantly().build(), "cactus_bundle");
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    public void onEntityCollision(BlockState blockState, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.damage(NDamageSources.CACTUS_BUNDLE, 1.0F * entityIn.fallDistance);
    }

}