package team.hollow.neutronia.api;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.utils.DataHolder;

import java.util.List;

public interface INotebookSection {
    Identifier getID();

    boolean isVisibleTo(DataHolder player);

    List<INotebookElement> getElements(DataHolder player, int page);

    int getPageCount(DataHolder player);
}