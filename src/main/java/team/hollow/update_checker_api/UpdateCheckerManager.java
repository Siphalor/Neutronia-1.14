package team.hollow.update_checker_api;

import team.hollow.update_checker_api.api.UpdateChecker;

import java.util.HashMap;
import java.util.Map;

public class UpdateCheckerManager {

    private static Map<String, UpdateChecker> updateCheckers = new HashMap<>();

    public static void registerUpdateChecker(String modId, UpdateChecker updateChecker) {
        updateCheckers.put(modId, updateChecker);
    }

    public static Map<String, UpdateChecker> getUpdateCheckers() {
        return updateCheckers;
    }

}