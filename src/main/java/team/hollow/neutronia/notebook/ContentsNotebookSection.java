package team.hollow.neutronia.notebook;

import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.NotebookElement;
import team.hollow.neutronia.api.NotebookSection;
import team.hollow.neutronia.init.NItems;

import java.util.ArrayList;
import java.util.List;

public class ContentsNotebookSection implements NotebookSection {

    private static List<BasicNotebookElements.ItemInfoButton> buttons = new ArrayList<>();

    static {
        buttons.add((BasicNotebookElements.ItemInfoButton) new BasicNotebookElements.ItemInfoButton(NotebookSectionRegistry.NEW_WOOD_TYPES, NItems.BLUEBERRY, "notebook.neutronia.new_wood_types.title", "notebook.neutronia.new_wood_types.desc").withPadding(5));
        buttons.add((BasicNotebookElements.ItemInfoButton) new BasicNotebookElements.ItemInfoButton(NotebookSectionRegistry.SAVANNA_REVAMP, Blocks.ACACIA_LOG, "notebook.neutronia.savanna_revamp.title", "notebook.neutronia.savanna_revamp.desc").withPadding(5));
        buttons.add((BasicNotebookElements.ItemInfoButton) new BasicNotebookElements.ItemInfoButton(NotebookSectionRegistry.FOREST_REVAMP, Blocks.OAK_LEAVES, "notebook.neutronia.forest_revamp.title", "notebook.neutronia.forest_revamp.desc").withPadding(5));
        buttons.add((BasicNotebookElements.ItemInfoButton) new BasicNotebookElements.ItemInfoButton(NotebookSectionRegistry.AQUATIC_REVAMP_V2, Blocks.PRISMARINE, "notebook.neutronia.aquatic_revamp_v2.title", "notebook.neutronia.aquatic_revamp_v2.desc").withPadding(5));
        buttons.add((BasicNotebookElements.ItemInfoButton) new BasicNotebookElements.ItemInfoButton(NotebookSectionRegistry.ENCHANTMEN_ADDITIONS, Items.ENCHANTED_BOOK, "notebook.neutronia.enchantment_additions.title", "notebook.neutronia.enchantment_additions.desc").withPadding(5));
    }

    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "contents");
    }

    @Override
    public List<NotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<NotebookElement> elements = new ArrayList<>();

        int textPages = BasicNotebookElements.textPages("notebook.neutronia.intro", 2);

        if (page == 0) {
            elements.add(new BasicNotebookElements.BigHeading("notebook.neutronia.title").withPadding(-3));
        }

        if (page <= textPages) {
            elements.addAll(BasicNotebookElements.wrapText("notebook.neutronia.intro", 2, 0, page));
        } else if (page == 1) {
            int number = 0;
            if (page == textPages + 1) {
                elements.add(new BasicNotebookElements.SmallHeading("notebook.neutronia.categories").withPadding(3));
            } else {
                elements.add(new BasicNotebookElements.Padding(10));
            }

            int thisPage = page - textPages;
            for (BasicNotebookElements.ItemInfoButton button : buttons) {
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
        for (BasicNotebookElements.ItemInfoButton button : buttons) {
            if (button.link.isVisibleTo(player)) {
                amount++;
            }
        }
        return (int) Math.ceil(amount / 4f) + BasicNotebookElements.textPages("notebook.neutronia.intro", 2) + amount;
    }
}