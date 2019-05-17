package team.hollow.neutronia.init;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.client.ClientInit;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class NModMenuCompat implements ModMenuApi {
    @Override
    public String getModId() {
        return Neutronia.MOD_ID;
    }

    @Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen) {
        return Optional.of(() -> ClientInit.tweedClothBridge.open());
    }
}
