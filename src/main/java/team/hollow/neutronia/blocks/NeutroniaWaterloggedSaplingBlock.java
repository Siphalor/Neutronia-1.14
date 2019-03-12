package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;

public class NeutroniaWaterloggedSaplingBlock extends SaplingBlock implements Waterloggable {

    public NeutroniaWaterloggedSaplingBlock(SaplingGenerator saplingGenerator_1) {
        super(saplingGenerator_1, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
    }

}