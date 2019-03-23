package team.hollow.neutronia.notebook;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;

import java.util.ArrayList;
import java.util.List;

public class CrystallizationNotebookSection implements INotebookSection {
    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "crystallization");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();
        if (page == 0) {
            elements.add(new NotebookElement.SmallHeading("notebook.neutronia.crystallization.title", MinecraftClient.getInstance().player.getEntityName()).withPadding(3));
            elements.add(new NotebookElement.Paragraph(false, 0.7, "notebook.neutronia.crystallization.0"));
        } else if (page == 1) {
            elements.add(new NotebookElement.Padding(8));
            elements.add(new NotebookElement.Paragraph(true, 0.8, "item.neutronia.coal_crystal").withPadding(10));
        } else if (page == 2) {
            elements.add(new NotebookElement.Padding(8));
            elements.add(new NotebookElement.Paragraph(true, 0.8, "item.neutronia.lapis_crystal").withPadding(10));
        } else if (page == 3) {
            elements.add(new NotebookElement.Padding(8));
            elements.add(new NotebookElement.Paragraph(true, 0.8, "item.neutronia.redstone_crystal").withPadding(10));
        } else if (page == 4) {
            elements.add(new NotebookElement.Padding(8));
            elements.add(new NotebookElement.Paragraph(true, 0.8, "item.neutronia.diamond_crystal").withPadding(10));
        } else if (page == 5) {
            elements.add(new NotebookElement.Padding(8));
            elements.add(new NotebookElement.Paragraph(true, 0.8, "item.neutronia.emerald_crystal").withPadding(10));
        }
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return 5;
    }
}