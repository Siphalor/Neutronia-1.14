package team.hollow.neutronia.addon;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.addon.client.ModMenuAddon;

@Environment(EnvType.CLIENT)
public class ClientAddonLoader {
    public static void loadAddons() {
        if (FabricLoader.getInstance().isModLoaded("modmenu")) {
            ModMenuAddon.loadModMenuAPI();
            Neutronia.testConfig.client.displayConfigButton = true;
        }
    }
}
