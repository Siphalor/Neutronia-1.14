package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;

public class NeutroniaLightRotatableBlock extends PillarBlock {

    public NeutroniaLightRotatableBlock(Material material, int lightLevel, float hardness, float resistance, BlockSoundGroup soundGroup) {
        super(FabricBlockSettings.of(material).lightLevel(lightLevel).hardness(hardness).resistance(resistance).sounds(soundGroup).build());
    }

}
