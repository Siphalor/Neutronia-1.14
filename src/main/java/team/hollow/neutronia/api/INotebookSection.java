package team.hollow.neutronia.api;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public interface INotebookSection {
    Identifier getID();

    List<INotebookElement> getElements(ClientPlayerEntity player, int page);

    int getPageCount(ClientPlayerEntity player);
}