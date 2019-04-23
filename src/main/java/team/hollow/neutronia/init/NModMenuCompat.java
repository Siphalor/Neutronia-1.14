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
        return Optional.of(this::openGui);
    }

    private Screen openGui() {
        ConfigScreenBuilder builder = ConfigScreenBuilder.create(MinecraftClient.getInstance().currentScreen, "Cloth Mod Config Demo", null);
        builder.addCategory("Overworld").addOption(new BooleanListEntry("Simple Boolean", false, null))
                .setBackgroundTexture(new Identifier("minecraft", "textures/block/dirt.png"));
        ConfigScreenBuilder.CategoryBuilder numberZone = builder.addCategory("Nether");
        numberZone.setBackgroundTexture(new Identifier("minecraft", "textures/block/netherrack.png"));
        numberZone.addOption(new StringListEntry("§aString §bField", "ab", "§cReset",
                () -> "ab", null));
        numberZone.addOption((new IntegerListEntry("§eInteger Field", 2, "§cReset",
                () -> 2, null)).setMaximum(99).setMinimum(2));
        numberZone.addOption(new IntegerSliderEntry("§bInteger Slider", 2, 99, 99, "§cReset",
                () -> 2, null));
        List<ClothConfigScreen.AbstractListEntry> randomCategory = Lists.newArrayList();
        randomCategory.add(new TextListEntry("x", "§eThis is a promotional message brought to you by Danielshe. Shop your favorite Lil Tater at " + TextFormat.BOLD + TextFormat.UNDERLINE + TextFormat.OBFUSCATED + "store.liltater.com!",
                -1, () -> Optional.of(new String[]{"§kThis is an example tooltip."})));
        randomCategory.add(new SubCategoryListEntry("§9Sub-Sub-Category",
                ImmutableList.of(
                        new IntegerSliderEntry("Integer Slider", 2, 99, 99, "text.cloth-config.reset_value",
                                () -> 2, null),
                        new IntegerSliderEntry("Integer Slider 2", 2, 99, 99, "text.cloth-config.reset_value",
                                () -> 4, null)),
                        false
                )
        );
        numberZone.addOption(new SubCategoryListEntry("§6Test", randomCategory, false));

        ConfigScreenBuilder.CategoryBuilder end = builder.addCategory("End");
        end.setBackgroundTexture(new Identifier("minecraft", "textures/block/end_stone.png"));

        return builder.build();
    }
}
