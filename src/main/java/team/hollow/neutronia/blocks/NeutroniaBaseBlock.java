package team.hollow.neutronia.blocks;

import net.minecraft.block.Material;
import team.hollow.neutronia.INeutroniaInfo;

public class NeutroniaBaseBlock extends BaseModBlock implements INeutroniaInfo {

    public NeutroniaBaseBlock(Material material, String name) {
        super(material, name);
    }

    public NeutroniaBaseBlock(Settings builder, String name) {
        super(builder, name);
    }

    public NeutroniaBaseBlock(Material material, String name, float hardness, float resistant) {
        super(Settings.of(material).strength(hardness, resistant), name);
    }

}