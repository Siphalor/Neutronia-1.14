package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class NeutroniaBaseBlock extends BaseModBlock {

    public NeutroniaBaseBlock(Material material, String name) {
        super(material, name);
    }

    public NeutroniaBaseBlock(FabricBlockSettings builder, String name) {
        super(builder, name);
    }

    public NeutroniaBaseBlock(Material material, String name, float hardness, float resistant) {
        super(FabricBlockSettings.of(material).strength(hardness, resistant), name);
    }

}