package team.abnormals.neutronia.blocks;

import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;

public class BlockNeutroniaDoor extends BlockModDoor implements INeutroniaBlock {

    public BlockNeutroniaDoor(String name) {
        this(Material.WOOD, name);
    }

    public BlockNeutroniaDoor(Material material, String name) {
        super(material, name, ItemGroup.REDSTONE);
    }

}