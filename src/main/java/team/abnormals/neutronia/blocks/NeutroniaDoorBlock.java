package team.abnormals.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import team.abnormals.neutronia.INeutroniaInfo;

public class NeutroniaDoorBlock extends DoorModBlock implements INeutroniaInfo {

    public NeutroniaDoorBlock(String name) {
        this(Material.WOOD, name);
    }

    public NeutroniaDoorBlock(Material material, String name) {
        super(material, name, ItemGroup.REDSTONE);
    }

}