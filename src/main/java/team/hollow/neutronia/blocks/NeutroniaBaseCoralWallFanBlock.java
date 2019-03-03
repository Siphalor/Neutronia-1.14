package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.CoralWallFanBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import team.hollow.neutronia.IMinecraftInfo;

public class NeutroniaBaseCoralWallFanBlock extends CoralWallFanBlock implements IMinecraftInfo {

    public NeutroniaBaseCoralWallFanBlock(Block deadBlock) {
        super(deadBlock, FabricBlockSettings.of(Material.STONE, MaterialColor.RED).strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL).build());
    }

}
