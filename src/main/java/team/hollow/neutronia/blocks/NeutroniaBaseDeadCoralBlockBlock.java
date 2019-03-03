package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import team.hollow.neutronia.IMinecraftInfo;

public class NeutroniaBaseDeadCoralBlockBlock extends BaseModBlock implements IMinecraftInfo {

    public NeutroniaBaseDeadCoralBlockBlock(String name) {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.RED).strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL), name);
    }

}
