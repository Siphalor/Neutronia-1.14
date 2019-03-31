package team.hollow.neutronia.api.groups;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public interface IComponent {

    void onInit();

    @Environment(EnvType.CLIENT)
    default void onInitClient() {

    }

}
