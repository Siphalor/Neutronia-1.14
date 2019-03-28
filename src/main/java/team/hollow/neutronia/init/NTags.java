package team.hollow.neutronia.init;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;

public class NTags {

    public static final Tag<Block> LECTERNS = registerBlockTag("lecterns");
    public static final Tag<Block> CLIMBABLE = registerBlockTag("climbable");
    public static final Tag<Block> BARD_POI = registerBlockTag("bard_poi");

    public static Tag<Block> registerBlockTag(String id) {
        return TagRegistry.block(new Identifier(Neutronia.MOD_ID, id));
    }

}
