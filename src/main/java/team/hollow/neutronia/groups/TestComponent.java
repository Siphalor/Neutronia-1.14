package team.hollow.neutronia.groups;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import team.hollow.neutronia.api.groups.IComponent;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class TestComponent implements IComponent {

    @Override
    public void onInit() {
        RegistryUtils.register(new Block(FabricBlockSettings.of(Material.STONE).build()), "test");
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitClient() {

    }

}
