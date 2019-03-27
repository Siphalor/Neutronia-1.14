package team.hollow.neutronia.notebook;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;
import team.hollow.neutronia.init.NBlocks;
import team.hollow.neutronia.init.NItems;

import java.util.ArrayList;
import java.util.List;

public class ContentsNotebookSection implements INotebookSection {

    private static List<NotebookElement.ItemInfoButton> buttons = new ArrayList<>();

    static {
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.DISCOVERY, NItems.BLUEBERRY, "notebook.neutronia.discovery.title", "notebook.neutronia.discovery.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.TRANSFIGURATION, NItems.GOOSEBERRIES, "notebook.neutronia.transfiguration.title", "notebook.neutronia.transfiguration.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.CRYSTALLIZATION, NItems.GREEN_GRAPES, "notebook.neutronia.crystallization.title", "notebook.neutronia.crystallization.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.SMELTING, NItems.WITHER_BERRIES, "notebook.neutronia.smelting.title", "notebook.neutronia.smelting.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.ARMOURY, NBlocks.BOOKSHELVES[1], "notebook.neutronia.armoury.title", "notebook.neutronia.armoury.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.neutronia.infusion.title", "notebook.neutronia.infusion.desc").withPadding(5));
    }

    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "contents");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();

        int textPages = NotebookElement.textPages("notebook.neutronia.intro", 2);

        if (page == 0) {
            String name = MinecraftClient.getInstance().player.getEntityName();
            elements.add(new NotebookElement.BigHeading("notebook.neutronia.title", name.substring(0, 1).toUpperCase() + name.substring(1)).withPadding(-3));
        }

        if (page <= textPages) {
            elements.addAll(NotebookElement.wrapText("notebook.neutronia.intro", 2, 0, page));
        } else if (page == 1) {
            int number = 0;

            if (page == textPages + 1) {
                elements.add(new NotebookElement.SmallHeading("notebook.neutronia.categories").withPadding(3));
            } else {
                elements.add(new NotebookElement.Padding(10));
            }

            int thisPage = page - textPages;

            for (NotebookElement.ItemInfoButton button : buttons) {
                if (button.link.isVisibleTo(player)) {
                    number++;
                    int properPage = (int) Math.ceil(number / 4f);
                    if (properPage == thisPage) {
                        elements.add(button);
                    } else if (properPage > thisPage) {
                        break;
                    }
                }
            }
        }
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        int amount = 0;
        for (NotebookElement.ItemInfoButton button : buttons) {
            if (button.link.isVisibleTo(player)) {
                amount++;
            }
        }
        return (int) Math.ceil(amount / 4f) + NotebookElement.textPages("notebook.neutronia.intro", 2);
    }
}