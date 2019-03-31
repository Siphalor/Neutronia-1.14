package team.hollow.neutronia.api.groups;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class Component {

    public Group group;
    private IComponent component;

    public Component(IComponent component) {
        this.component = component;
    }

    void init() {
        component.onInit();
    }

    @Environment(EnvType.CLIENT)
    void initClient() {
        component.onInitClient();
    }

}