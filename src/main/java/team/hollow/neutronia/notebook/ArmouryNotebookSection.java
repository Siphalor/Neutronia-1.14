package team.hollow.neutronia.notebook;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;

import java.util.ArrayList;
import java.util.List;

public class ArmouryNotebookSection implements INotebookSection {

    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "armoury");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();
        if (page == 0) {
            elements.add(new NotebookElement.SmallHeading("notebook.neutronia.armoury.title").withPadding(3));
        } else {
            elements.add(new NotebookElement.Padding(3));
        }

        int firstText = NotebookElement.textPages("notebook.neutronia.armoury.0", 2);
        elements.addAll(NotebookElement.wrapText("notebook.neutronia.armoury.0", 2, 0, page));

        if (page == firstText + 1) {
            elements.add(new NotebookElement.Padding(4));
            elements.add(new NotebookElement.Paragraph(true, 1, "item.neutronia.iron_dagger").withPadding(10));
            elements.add(new NotebookElement.Recipe(MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(Neutronia.MOD_ID, "acacia_bookshelf")).orElse(null)));
        }

        if (page >= firstText + 2) {
            elements.addAll(NotebookElement.wrapText("notebook.neutronia.armoury.1", 0, firstText + 2, page));
        }
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return NotebookElement.textPages("notebook.neutronia.armoury.0", 2) + NotebookElement.textPages("notebook.neutronia.armoury.1", 0) + 2;
    }
}