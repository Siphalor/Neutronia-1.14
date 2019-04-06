package team.hollow.neutronia.addon.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuAddon {

    public static void loadModMenuAPI() {
        /*if(FabricLoader.getInstance().isModLoaded("cloth")) {
            ModMenuApi.addConfigOverride(Neutronia.MOD_ID, () -> Neutronia.openConfigScreen(MinecraftClient.getInstance().currentScreen));
        }*/
    }
}
