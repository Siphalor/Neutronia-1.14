package team.hollow.neutronia.client;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import team.hollow.neutronia.api.ConfigScreenBuilder;
import team.hollow.neutronia.client.gui.entries.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class NCInitializer implements ClientModInitializer {

    public void onInitializeClient() {
        new AtomicBoolean(false);
        if (FabricLoader.getInstance().isModLoaded("modmenu")) {
            ModMenuApi.addConfigOverride("neutronia", this::openGui);
        }
    }

    public void openGui() {
        ConfigScreenBuilder builder = ConfigScreenBuilder.create(MinecraftClient.getInstance().currentScreen, "Cloth Mod Config Demo", null);
        builder.addCategory("Boolean Zone").addOption(new BooleanListEntry("Simple Boolean", false, null));
        ConfigScreenBuilder.CategoryBuilder numberZone = builder.addCategory("Numbers Zone");
        numberZone.addOption(new StringListEntry("String Field", "ab", "text.cloth-config.reset_value",
                () -> "ab", null));
        numberZone.addOption((new IntegerListEntry("Integer Field", 2, "text.cloth-config.reset_value",
                () -> 2, null)).setMaximum(99).setMinimum(2));
        numberZone.addOption(new IntegerSliderEntry("Integer Slider", 2, 99, 99, "text.cloth-config.reset_value",
                () -> 2, null));
        ConfigScreenBuilder.CategoryBuilder enumZone = builder.addCategory("Enum Zone");
        enumZone.addOption(new EnumListEntry<>("Enum Field", DemoEnum.class, DemoEnum.CONSTANT_2,
                "text.cloth-config.reset_value", () -> DemoEnum.CONSTANT_1, null));
        MinecraftClient.getInstance().openScreen(builder.build());
    }

    private enum DemoEnum {
        CONSTANT_1("Constant 1"),
        CONSTANT_2("Constant 2"),
        CONSTANT_3("Constant 3");

        private final String key;

        DemoEnum(String key) {
            this.key = key;
        }

        public String toString() {
            return this.key;
        }
    }
}
