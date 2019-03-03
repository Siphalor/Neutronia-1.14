package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public abstract class BaseModBlock extends Block {

    public BaseModBlock(Material material, String name) {
        super(FabricBlockSettings.of(material).build());
        RegistryUtils.register(new Identifier(Neutronia.MOD_ID, name), this, ItemGroup.BUILDING_BLOCKS);
    }

    public BaseModBlock(Material material, String name, ItemGroup itemGroup) {
        super(FabricBlockSettings.of(material).build());
        RegistryUtils.register(new Identifier(Neutronia.MOD_ID, name), this, itemGroup);
    }

    public BaseModBlock(FabricBlockSettings builder, String name) {
        super(builder.build());
        RegistryUtils.register(new Identifier(Neutronia.MOD_ID, name), this, ItemGroup.BUILDING_BLOCKS);
    }

    public BaseModBlock(FabricBlockSettings builder, Identifier name) {
        super(builder.build());
        RegistryUtils.register(name, this, ItemGroup.BUILDING_BLOCKS);
    }

}