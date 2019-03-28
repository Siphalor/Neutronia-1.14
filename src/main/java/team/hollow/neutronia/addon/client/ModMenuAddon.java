/*
package team.hollow.neutronia.addon.client;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.client.gui.ModListScreen;

@Environment(EnvType.CLIENT)
public class ModMenuAddon {

    public static void loadModMenuAPI() {
        ModMenuApi.addConfigOverride(Neutronia.MOD_ID, () -> {
            MinecraftClient.getInstance().openScreen(new ModListScreen(MinecraftClient.getInstance().currentScreen));
        });
    }
}
*/
