package team.hollow.neutronia.client.gui;

import com.google.common.base.Strings;
import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.Validate;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.config.ConfigFile;
import team.hollow.neutronia.config.ConfigManager;

import java.io.InputStream;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ModListScreen extends Screen {
    private final Screen parent;
    private List<ModMetadata> mods;
    private ModListWidget modListWidget;

    public ModListScreen(Screen parent) {
        super(new TranslatableTextComponent("screen.knit.mod.config.edit"));
        this.parent = parent;
        this.mods = ConfigManager.getInstance().getModList();
    }

    @Override
    protected void init() {
        super.init();
        this.modListWidget = new ModListWidget(this, this.minecraft);
        this.children.add(this.modListWidget);
        this.setFocused(this.modListWidget);
        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 25, 200, 20,
                I18n.translate("gui.done"), (buttonWidget) -> this.minecraft.openScreen(this.parent)));
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        this.renderBackground();
        this.modListWidget.render(int_1, int_2, float_1);
        this.drawCenteredString(this.minecraft.textRenderer, this.title.getFormattedText(), this.width / 2, 15, 0xffffff);
        super.render(int_1, int_2, float_1);

    }

    private static class ModListWidget extends EntryListWidget<ModEntry> {
        private ModListWidget(ModListScreen modListscreen, MinecraftClient client) {
            super(client, modListscreen.width, modListscreen.height, 30,
                    modListscreen.height - 40, 25);
            for (ModMetadata metadata : modListscreen.mods) {
                this.addEntry(new ModEntry(modListscreen, metadata));
            }
        }
    }

    private static class ModEntry extends EntryListWidget.Entry<ModEntry> implements ButtonWidget.class_4241 {
        private static final Identifier UNKNOWN = new Identifier("textures/misc/unknown_pack.png");
        private ButtonWidget openConfigBtn;
        private final ModMetadata modMetadata;
        private final ModListScreen parentScreen;
        private final Identifier modIconLocation;
        private final NativeImageBackedTexture modIcon;

        private ModEntry(ModListScreen parentScreen, ModMetadata modMetadata) {
            this.modMetadata = modMetadata;
            this.parentScreen = parentScreen;
            this.openConfigBtn = new ButtonWidget(0, 0,
                    200, 20, Strings.isNullOrEmpty(modMetadata.getName()) ? modMetadata.getId() : modMetadata.getName(), this);
            this.modIconLocation = new Identifier(Neutronia.MOD_ID, modMetadata.getId() + "_icon");
            this.modIcon = getNativeModIcon();
        }

        @Override
        public void draw(int index, int y, int x, int width, int height, int mouseX, int mouseY, boolean mouseOver, float delta) {
            openConfigBtn.x = x + 10;
            openConfigBtn.y = y;
            openConfigBtn.render(mouseX, mouseY, delta);

            GlStateManager.pushMatrix();
            GlStateManager.color4f(1f, 1f, 1f, 1f);
            parentScreen.minecraft.getTextureManager().bindTexture(modIcon != null ? modIconLocation : UNKNOWN);
            blit(x + 15, y + 2, 0f, 0f, 16, 16, 16f, 16f);
            GlStateManager.popMatrix();
        }

        @Override
        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            if (openConfigBtn.mouseClicked(double_1, double_2, int_1))
                return true;
            return super.mouseClicked(double_1, double_2, int_1);
        }

        @Override
        public void onPress(ButtonWidget var1) {
            List<ConfigFile> configs = ConfigManager.getInstance().getModConfigs(modMetadata.getId());
            if (configs.size() == 1)
                MinecraftClient.getInstance().openScreen(new ModConfigScreen(parentScreen, modMetadata, configs.get(0)));
            else if (configs.size() > 1)
                MinecraftClient.getInstance().openScreen(new ModMultiConfigScreen(parentScreen, modMetadata, configs));
        }

        // Copied from ModMenu
        private NativeImageBackedTexture getNativeModIcon() {
            try {
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("assets/" + this.modMetadata.getId() + "/icon.png");
                Throwable streamError = null;
                NativeImageBackedTexture modIcon;
                try {
                    NativeImage var4 = NativeImage.fromInputStream(stream);
                    Validate.validState(var4.getHeight() == var4.getWidth(), "Must be square icon");
                    NativeImageBackedTexture nativeImage = new NativeImageBackedTexture(var4);
                    this.parentScreen.minecraft.getTextureManager().registerTexture(this.modIconLocation, nativeImage);
                    modIcon = nativeImage;
                } catch (Throwable thr) {
                    streamError = thr;
                    throw thr;
                } finally {
                    if (stream != null) {
                        if (streamError != null) {
                            try {
                                stream.close();
                            } catch (Throwable thr) {
                                streamError.addSuppressed(thr);
                            }
                        } else {
                            stream.close();
                        }
                    }
                }
                return modIcon;
            } catch (Throwable var16) {
                Neutronia.getLogger().error("Invalid icon for mod {}", this.modMetadata.getName(), var16);
                return null;
            }
        }

    }
}
