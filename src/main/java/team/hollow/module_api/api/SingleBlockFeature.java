package team.hollow.module_api.api;

import net.minecraft.block.Block;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class SingleBlockFeature extends OptionalFeature {
    public Block block;
    
    public SingleBlockFeature(Block block1, String name) {
        super(name, "Adds " + formatName(name));
        block = block1;
    }

    @Override
    protected void applyEnabled() {
        RegistryUtils.register(block, name);
    }
}