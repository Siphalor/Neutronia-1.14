package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class NeutroniaBaseCoralBlockBlock extends CoralBlockBlock {

    public NeutroniaBaseCoralBlockBlock(Block deadBlock, String name) {
        super(deadBlock, FabricBlockSettings.of(Material.STONE, MaterialColor.RED).strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL).build());
    }

}
