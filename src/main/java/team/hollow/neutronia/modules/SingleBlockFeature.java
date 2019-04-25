package team.hollow.neutronia.modules;

import net.minecraft.block.Block;
import org.apache.commons.lang3.text.WordUtils;
import team.hollow.module_api.api.OptionalFeature;
import team.hollow.neutronia.utils.registry.RegistryUtils;

public class SingleBlockFeature extends OptionalFeature {
    public Block block;
    
    public SingleBlockFeature(Block block1, String name) {
        super(name, String.format("Adds %s", WordUtils.capitalizeFully(name.replace("_", " "))));
        block = block1;
    }

    @Override
    protected void applyEnabled() {
        RegistryUtils.register(block, name);
    }

}