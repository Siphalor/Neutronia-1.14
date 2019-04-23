package team.hollow.module_api;

import net.minecraft.util.Identifier;
import team.hollow.module_api.api.Module;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    private static Map<Identifier, Module[]> modulesMap = new HashMap<>();

    public static void registerModule(Identifier moduleIdentifier, Module... modules) {
        modulesMap.put(moduleIdentifier, modules);
        System.out.println(modulesMap);
    }

    public static Map<Identifier, Module[]> getModulesMap() {
        return modulesMap;
    }

}