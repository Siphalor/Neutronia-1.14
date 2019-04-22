package team.hollow.modmenu_api;

import team.hollow.modmenu_api.api.ModMenuBadge;

import java.util.HashMap;
import java.util.Map;

public class ModMenuBadgeManager {

    private static Map<String, ModMenuBadge[]> badges = new HashMap<>();

    public static void registerBadges(String modId, ModMenuBadge... modMenuBadges) {
        badges.put(modId, modMenuBadges);
    }

    public static Map<String, ModMenuBadge[]> getBadges() {
        return badges;
    }

}