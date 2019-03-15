package team.hollow.neutronia.notebook;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;

import java.util.ArrayList;
import java.util.List;

public class TransfigurationNotebookSection implements INotebookSection {
    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "transfiguration");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();
        if (page == 0) {
            elements.add(new NotebookElement.SmallHeading("notebook.arcanemagic.transfiguration.title").withPadding(3));
        } else {
            elements.add(new NotebookElement.Padding(3));
        }

        int firstText = NotebookElement.textPages("notebook.arcanemagic.transfiguration.0", 2);
        elements.addAll(NotebookElement.wrapText("notebook.arcanemagic.transfiguration.0", 2, 0, page));

        if (page == firstText + 1) {
            elements.add(new NotebookElement.Padding(4));
            elements.add(new NotebookElement.Paragraph(true, 1, "block.arcanemagic.transfiguration_table").withPadding(10));
            elements.add(new NotebookElement.Recipe(MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(Neutronia.MOD_ID, "transfiguration_table")).orElse(null)));
        }

        if (page >= firstText + 2) {
            int secondText = NotebookElement.textPages("notebook.arcanemagic.transfiguration.1", 0) + firstText + 2;
            elements.addAll(NotebookElement.wrapText("notebook.arcanemagic.transfiguration.1", 0, firstText + 2, page));

            if (page == secondText + 1) {
                elements.add(new NotebookElement.Padding(4));
                elements.add(new NotebookElement.Paragraph(true, 1, "item.arcanemagic.gold_crystal").withPadding(8));
                elements.add(new NotebookElement.Recipe(MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(Neutronia.MOD_ID, "gold_crystal")).orElse(null)));
            }
        }
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return NotebookElement.textPages("notebook.arcanemagic.transfiguration.0", 2) + NotebookElement.textPages("notebook.arcanemagic.transfiguration.1", 0) + 3;
    }
}