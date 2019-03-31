package team.hollow.neutronia.notebook;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.NotebookElement;
import team.hollow.neutronia.api.NotebookSection;

import java.util.ArrayList;
import java.util.List;

public class NewWoodVariantsNotebookSection implements NotebookSection {

    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "new_wood_variants");
    }

    @Override
    public List<NotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<NotebookElement> elements = new ArrayList<>();
        if (page == 0) {
            elements.add(new BasicNotebookElements.SmallHeading("notebook.neutronia.new_wood_variants.title").withPadding(3));
        } else {
            elements.add(new BasicNotebookElements.Padding(3));
        }

        elements.addAll(BasicNotebookElements.wrapText("notebook.neutronia.transfiguration.0", 2, 0, page));

        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return BasicNotebookElements.textPages("notebook.neutronia.discovery.0", 2);
    }
}