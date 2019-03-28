package team.hollow.neutronia.notebook;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.api.INotebookSection;

import java.util.HashMap;
import java.util.Map;

public class NotebookSectionRegistry {
    private static final Map<Identifier, INotebookSection> REGISTRY = new HashMap<>();

    public static final INotebookSection NEW_WOOD_TYPES = register(new NewWoodVariantsNotebookSection());
    public static final INotebookSection SAVANNA_REVAMP = register(new SavannaRevampNotebookSection());
    public static final INotebookSection FOREST_REVAMP = register(new ForestRevampNotebookSection());
    public static final INotebookSection AQUATIC_REVAMP_V2 = register(new AquaticRevampV2NotebookSection());
    public static final INotebookSection ENCHANTMEN_ADDITIONS = register(new EnchantmentAdditionsNotebookSection());

    public static final INotebookSection CONTENTS = register(new ContentsNotebookSection());

    public static INotebookSection get(Identifier id) {
        if (id != null && REGISTRY.containsKey(id)) {
            return REGISTRY.get(id);
        }
        return null;
    }

    private static INotebookSection register(INotebookSection section) {
        REGISTRY.put(section.getID(), section);
        return section;
    }
}