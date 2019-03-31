package team.hollow.neutronia.notebook;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.api.NotebookSection;

import java.util.HashMap;
import java.util.Map;

public class NotebookSectionRegistry {
    private static final Map<Identifier, NotebookSection> REGISTRY = new HashMap<>();

    public static final NotebookSection NEW_WOOD_TYPES = register(new NewWoodVariantsNotebookSection());
    public static final NotebookSection SAVANNA_REVAMP = register(new SavannaRevampNotebookSection());
    public static final NotebookSection FOREST_REVAMP = register(new ForestRevampNotebookSection());
    public static final NotebookSection AQUATIC_REVAMP_V2 = register(new AquaticRevampV2NotebookSection());
    public static final NotebookSection ENCHANTMEN_ADDITIONS = register(new EnchantmentAdditionsNotebookSection());

    public static final NotebookSection CONTENTS = register(new ContentsNotebookSection());

    public static NotebookSection get(Identifier id) {
        if (id != null && REGISTRY.containsKey(id)) {
            return REGISTRY.get(id);
        }
        return null;
    }

    private static NotebookSection register(NotebookSection section) {
        REGISTRY.put(section.getID(), section);
        return section;
    }

}