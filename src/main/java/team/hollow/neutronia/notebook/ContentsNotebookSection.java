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
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.DISCOVERY, NItems.BLUEBERRY, "notebook.arcanemagic.discovery.title", "notebook.arcanemagic.discovery.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.TRANSFIGURATION, NItems.GOOSEBERRIES, "notebook.arcanemagic.transfiguration.title", "notebook.arcanemagic.transfiguration.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.CRYSTALLIZATION, NItems.GREEN_GRAPES, "notebook.arcanemagic.crystallization.title", "notebook.arcanemagic.crystallization.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.SMELTING, NItems.WITHER_BERRIES, "notebook.arcanemagic.smelting.title", "notebook.arcanemagic.smelting.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.ARMOURY, NBlocks.BOOKSHELVES[1], "notebook.arcanemagic.armoury.title", "notebook.arcanemagic.armoury.desc").withPadding(5));
        buttons.add((NotebookElement.ItemInfoButton) new NotebookElement.ItemInfoButton(NotebookSectionRegistry.INFUSION, NBlocks.BOOKSHELVES[2], "notebook.arcanemagic.infusion.title", "notebook.arcanemagic.infusion.desc").withPadding(5));
    }

    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "contents");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();

        if (page == 0) {
            String name = MinecraftClient.getInstance().player.getEntityName();
            elements.add(new NotebookElement.BigHeading("notebook.arcanemagic.title", name.substring(0, 1).toUpperCase() + name.substring(1)).withPadding(-3));
            elements.add(new NotebookElement.Paragraph(false, 0.7, "notebook.arcanemagic.intro"));
        } else if (page == 1) {
            elements.add(new NotebookElement.SmallHeading("notebook.arcanemagic.categories").withPadding(3));
            int amount = 0;

            for (NotebookElement.ItemInfoButton button : buttons) {
                elements.add(button);
                amount++;
                if (amount >= 4) {
                    break;
                }
            }
        }
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        int amount = 0;
        for (NotebookElement.ItemInfoButton button : buttons) {
            amount++;
        }
        return (int) Math.ceil(amount / 4f) + NotebookElement.textPages("notebook.arcanemagic.intro", 2);
    }
}