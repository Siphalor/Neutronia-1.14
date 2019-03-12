package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class CakeBaseBlock extends CakeBlock{

    public CakeBaseBlock(String name) {
        super(FabricBlockSettings.of(Material.CAKE).hardness(0.5F).sounds(BlockSoundGroup.WOOL).build());
    }

}
