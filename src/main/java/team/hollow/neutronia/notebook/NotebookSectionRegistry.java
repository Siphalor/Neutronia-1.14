package team.hollow.neutronia.notebook;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.api.INotebookSection;

import java.util.HashMap;
import java.util.Map;

public class NotebookSectionRegistry {
    private static final Map<Identifier, INotebookSection> REGISTRY = new HashMap<>();

    public static final INotebookSection CONTENTS = register(new ContentsNotebookSection());
    static final INotebookSection CRYSTALLIZATION = register(new CrystallizationNotebookSection());

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