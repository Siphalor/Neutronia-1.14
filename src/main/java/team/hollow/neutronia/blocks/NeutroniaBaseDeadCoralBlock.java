package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class NeutroniaBaseDeadCoralBlock extends DeadCoralBlock {

    public NeutroniaBaseDeadCoralBlock(String name) {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.RED).strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL).build());
    }

}
