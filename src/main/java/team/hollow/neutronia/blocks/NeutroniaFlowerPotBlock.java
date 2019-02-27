package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.INeutroniaInfo;

public class NeutroniaFlowerPotBlock extends FlowerPotBlock implements INeutroniaInfo {

    public NeutroniaFlowerPotBlock(String name, Block block_1) {
        super(block_1, FabricBlockSettings.of(Material.PART).breakInstantly().build());
        register(name, this);
    }

    private void register(String name, Block block) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
    }

}
