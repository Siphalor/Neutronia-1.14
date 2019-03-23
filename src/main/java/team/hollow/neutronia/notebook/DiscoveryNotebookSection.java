package team.hollow.neutronia.notebook;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryNotebookSection implements INotebookSection {
    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "discovery");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();
        if (page == 0) {
            elements.add(new NotebookElement.SmallHeading("notebook.neutronia.discovery.title").withPadding(3));
        } else {
            elements.add(new NotebookElement.Padding(3));
        }
        elements.addAll(NotebookElement.wrapText("notebook.neutronia.discovery.0", 2, 0, page));
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return NotebookElement.textPages("notebook.neutronia.discovery.0", 2);
    }
}