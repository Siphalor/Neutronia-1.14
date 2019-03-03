package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.DeadCoralFanBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import team.hollow.neutronia.IMinecraftInfo;

public class NeutroniaBaseDeadCoralFanBlock extends DeadCoralFanBlock implements IMinecraftInfo {

    public NeutroniaBaseDeadCoralFanBlock() {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.RED).strength(1.5F, 6.0F).sounds(BlockSoundGroup.CORAL).build());
    }

}
