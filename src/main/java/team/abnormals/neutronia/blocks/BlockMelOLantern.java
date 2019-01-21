package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class BlockMelOLantern extends BlockNeutroniaBase {

    public BlockMelOLantern() {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).lightLevel(15).build(), "mel_o_lantern");
    }

}