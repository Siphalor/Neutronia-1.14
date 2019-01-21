package team.abnormals.neutronia.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMelOLantern extends BlockNeutroniaBase {

    public BlockMelOLantern() {
        super("mel_o_lantern", Material.GOURD);
        setSoundType(SoundType.WOOD);
        setLightLevel(15);
    }

}