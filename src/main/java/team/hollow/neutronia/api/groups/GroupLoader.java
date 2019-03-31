package team.hollow.neutronia.api.groups;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import team.hollow.neutronia.Neutronia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class GroupLoader {

    public static List<Group> groups;
    public static Map<Class<? extends IComponent>, IComponent> componentInstances = new HashMap<>();

    static {
        groups = new ArrayList<>();
    }

    public static void init() {
        forEachGroup(Group::init);
    }

    @Environment(EnvType.CLIENT)
    public static void clientInit() {
        forEachGroup(Group::initClient);
    }

    private static void forEachGroup(Consumer<Group> consumer) {
        groups.forEach(consumer);
    }

    public static void registerGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            if (!group.name.isEmpty())
                Neutronia.getLogger().info("Registering Group " + group.name);
        }
    }

}
