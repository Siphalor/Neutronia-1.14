package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;

public class NeutroniaSaplingBlock extends SaplingBlock {

    public NeutroniaSaplingBlock(SaplingGenerator saplingGenerator_1) {
        super(saplingGenerator_1, FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build());
    }

}