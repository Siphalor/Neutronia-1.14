package team.hollow.neutronia.api;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public interface INotebookSection {

    default boolean isVisibleTo(ClientPlayerEntity player) {
        return true;
    }

    Identifier getID();

    List<INotebookElement> getElements(ClientPlayerEntity player, int page);

    int getPageCount(ClientPlayerEntity player);
}