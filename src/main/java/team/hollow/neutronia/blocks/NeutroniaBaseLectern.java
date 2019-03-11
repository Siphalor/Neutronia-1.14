package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.Material;

public class NeutroniaBaseLectern extends LecternBlock {

    public NeutroniaBaseLectern() {
        super(FabricBlockSettings.of(Material.WOOD).strength(2.5f, 1.0f).build());
    }

}