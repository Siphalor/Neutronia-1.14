package team.hollow.module_api.api.features;

import net.minecraft.block.Block;
import team.hollow.neutronia.registry.ContentBuilder;

public class SingleBlockFeature extends OptionalFeature {
    public Block block;
    
    public SingleBlockFeature(Block block1, String name) {
        super(name, "Adds " + formatName(name));
        block = block1;
    }

    @Override
    protected void applyEnabled() {
        block = ContentBuilder.getInstance().newBlock(name, block);
    }

}