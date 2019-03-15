package team.hollow.neutronia.api;

import net.minecraft.util.Identifier;
import team.hollow.neutronia.init.NConstants;
import team.hollow.neutronia.utils.DataHolder;

import java.util.List;

public interface INotebookSection {
    Identifier getID();

    boolean isVisibleTo(DataHolder player);

    List<INotebookElement> getElements(DataHolder player, int page);

    default boolean hasNewInfo(DataHolder player)
    {
        return player.getAdditionalData().getCompound(NConstants.NOTEBOOK_UPDATES_KEY).getBoolean(getID().toString());
    }

    int getPageCount(DataHolder player);
}