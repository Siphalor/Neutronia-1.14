package team.hollow.neutronia.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.ClothConfigScreen;
import me.shedaniel.cloth.gui.entries.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Identifier;
import team.hollow.module_api.ModuleManager;
import team.hollow.neutronia.Neutronia;

import java.util.List;
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
        return Optional.of(() -> ModuleManager.tweedClothBridge.open());
    }
}
