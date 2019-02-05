package team.abnormals.neutronia.blocks;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public class BlockNeutroniaFlowerPot extends FlowerPotBlock implements INeutroniaBlock {

    public BlockNeutroniaFlowerPot(String name, Block block_1) {
        super(block_1, FabricBlockSettings.of(Material.PART).breakInstantly().build());
        register(name, this);
    }

    private void register(String name, Block block) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
    }

}
