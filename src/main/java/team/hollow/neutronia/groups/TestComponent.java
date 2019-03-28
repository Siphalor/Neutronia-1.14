package team.hollow.neutronia.groups;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import team.hollow.neutronia.api.groups.Component;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class TestComponent extends Component {

    @Override
    public void init() {
        RegistryUtils.register(new Block(FabricBlockSettings.of(Material.STONE).build()), "test");
    }

}
