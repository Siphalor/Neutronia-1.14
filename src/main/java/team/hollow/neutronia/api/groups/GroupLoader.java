package team.hollow.neutronia.api.groups;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.commons.lang3.text.WordUtils;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.config.ConfigFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class GroupLoader {

    public static ConfigFile config;
    public static List<Group> groups;
    public static List<Group> enabledGroups;
    public static Map<Class<? extends Component>, Component> componentInstances = new HashMap<>();

    static {
        groups = new ArrayList<>();
    }

    public static void init() {
        forEachGroup(group -> setupConfig("config.neutronia." + group.name, group.configFile.getClass()));
        forEachGroup(module -> Neutronia.getLogger().info(WordUtils.capitalizeFully(module.name) + " is " + (module.enabled ? "enabled" : "disabled")));
        forEachGroup(Group::init);
    }

    @Environment(EnvType.CLIENT)
    public static void clientInit() {
        forEachEnabledGroup(Group::initClient);
    }

    public static void setupConfig(String configName, Class<?> configClass) {
        config = new ConfigFile(Neutronia.MOD_ID, configName, configClass);
        config.loadConfig();
    }

    private static void forEachGroup(Consumer<Group> consumer) {
        groups.forEach(consumer);
    }

    private static void forEachEnabledGroup(Consumer<Group> consumer) {
        enabledGroups.forEach(consumer);
    }

    public static void registerGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
            if (!group.name.isEmpty())
                Neutronia.getLogger().info("Registering Group " + group.name);
        }
    }

}
