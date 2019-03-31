package team.hollow.neutronia.notebook;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.NotebookElement;
import team.hollow.neutronia.api.NotebookSection;

import java.util.ArrayList;
import java.util.List;

public class ForestRevampNotebookSection implements NotebookSection {

    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "forest_revamp");
    }

    @Override
    public List<NotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<NotebookElement> elements = new ArrayList<>();
        if (page == 0) {
            elements.add(new BasicNotebookElements.SmallHeading("notebook.neutronia.forest_revamp.title").withPadding(3));
        } else {
            elements.add(new BasicNotebookElements.Padding(3));
        }

        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return BasicNotebookElements.textPages("notebook.neutronia.discovery.0", 2);
    }
}