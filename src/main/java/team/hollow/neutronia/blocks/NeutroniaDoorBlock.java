package team.hollow.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;

public class NeutroniaDoorBlock extends DoorModBlock {

    public NeutroniaDoorBlock(String name) {
        this(Material.WOOD, name);
    }

    public NeutroniaDoorBlock(Material material, String name) {
        super(material, name, ItemGroup.REDSTONE);
    }

}