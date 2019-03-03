package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class BarrelBaseBlock extends BarrelBlock {

    public BarrelBaseBlock(String name) {
        super(FabricBlockSettings.of(Material.WOOD).hardness(2.5F).sounds(BlockSoundGroup.WOOD).build());
        RegistryUtils.register(new Identifier(Neutronia.MOD_ID, name + "_barrel"), this, ItemGroup.BUILDING_BLOCKS);
    }

}
