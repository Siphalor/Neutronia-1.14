package team.hollow.neutronia.init;

import io.github.cottonmc.cotton.tags.TagEntryManager;
import io.github.cottonmc.cotton.tags.TagType;
import net.minecraft.util.Identifier;

public class NTags {

    static {
        registerTag(TagType.BLOCK, new Identifier("neutronia", "test"), "neutronia:test");
    }

    private static void registerTag(TagType type, Identifier tag, String... entries) {
        TagEntryManager.registerToTag(type, tag, entries);
    }

}
