package team.hollow.module_api;

import de.siphalor.tweed.client.TweedClothBridge;
import de.siphalor.tweed.config.ConfigEnvironment;
import de.siphalor.tweed.config.ConfigFile;
import de.siphalor.tweed.config.ConfigScope;
import de.siphalor.tweed.config.TweedRegistry;
import team.hollow.module_api.api.Module;
import team.hollow.neutronia.Neutronia;

import java.util.ArrayList;
import java.util.Arrays;

public class ModuleManager {
    private static ArrayList<Module> modules = new ArrayList<>();
    private static ConfigFile configFile;
    public static TweedClothBridge tweedClothBridge;

    public static void registerModule(Module... newModules) {
        modules.addAll(Arrays.asList(newModules));
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static void setup() {
        configFile = TweedRegistry.registerConfigFile(Neutronia.MOD_ID);

        modules.forEach(module -> configFile.register(module.name, module.getConfigCategory()));
        tweedClothBridge = new TweedClothBridge(configFile);

        configFile.setReloadListener(ModuleManager::apply);

        configFile.triggerInitialLoad();
    }

    public static void apply(ConfigEnvironment environment, ConfigScope scope) {
        if(scope.triggers(ConfigScope.GAME)) {
            modules.forEach(Module::apply);

            if(Neutronia.GEN_RESOURCES)
                System.exit(0);
        }
    }
}