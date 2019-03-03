package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class NeutroniaBaseLectern extends LecternBlock {

    public NeutroniaBaseLectern(String name) {
        super(FabricBlockSettings.of(Material.WOOD).strength(2.5f, 1.0f).build());
        RegistryUtils.register(new Identifier(Neutronia.MOD_ID, name + "_lectern"), this, ItemGroup.REDSTONE);
    }

}