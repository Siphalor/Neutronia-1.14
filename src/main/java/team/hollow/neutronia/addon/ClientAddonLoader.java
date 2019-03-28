package team.hollow.neutronia.addon;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientAddonLoader {
    public static void loadAddons() {
        /*if (FabricLoader.getInstance().isModLoaded("modmenu"))
            ModMenuAddon.loadModMenuAPI();*/
    }
}
