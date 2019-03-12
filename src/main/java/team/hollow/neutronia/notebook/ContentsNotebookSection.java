package team.hollow.neutronia.notebook;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;
import team.hollow.neutronia.init.NBlocks;
import team.hollow.neutronia.utils.DataHolder;

import java.util.ArrayList;
import java.util.List;

public class ContentsNotebookSection implements INotebookSection {
    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "contents");
    }

    @Override
    public boolean isVisibleTo(DataHolder player) {
        return true;
    }

    @Override
    public List<INotebookElement> getElements(DataHolder player, int page) {
        List<INotebookElement> elements = new ArrayList<>();
        List<NotebookElement.ItemInfoButton> buttons = new ArrayList<>();

        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.CRYSTALLIZATION, NBlocks.ACAN_CORAL, "notebook.arcanemagic.crystallization.title", "notebook.arcanemagic.crystallization.desc").withPadding(5));

        if (page == 0) {
            String name = MinecraftClient.getInstance().player.getEntityName();
            elements.add(new NotebookElement.BigHeading("notebook.arcanemagic.title", name.substring(0, 1).toUpperCase() + name.substring(1)).withPadding(-3));
            elements.add(new NotebookElement.Paragraph(false, 0.7, "notebook.arcanemagic.intro"));
        } else if (page == 1) {
            elements.add(new NotebookElement.SmallHeading("notebook.arcanemagic.categories").withPadding(3));
            int amount = 0;

            for (NotebookElement.ItemInfoButton button : buttons) {
                if (button.link.isVisibleTo(player)) {
                    elements.add(button);
                    amount++;
                }

                if (amount >= 4) {
                    break;
                }
            }
        }
        return elements;
    }

    @Override
    public int getPageCount(DataHolder player) {
        return 1;
    }
}