//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.client.gui;

import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.class_751;
import net.minecraft.class_766;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.ingame.ConfirmChatLinkScreen;
import net.minecraft.client.gui.menu.*;
import net.minecraft.client.gui.menu.settings.LanguageSettingsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.LanguageButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.TextFormat;
import net.minecraft.util.ChatUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.level.storage.LevelStorage;
import team.hollow.neutronia.Neutronia;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Environment(EnvType.CLIENT)
public class MainMenuScreen extends Screen {
    public static final class_751 field_17774 = new class_751(new Identifier("textures/gui/title/background/panorama"));
    private static final Identifier field_17775 = new Identifier("textures/gui/title/background/panorama_overlay.png");
    private final boolean field_17776;
    private String splashText;
    private ButtonWidget field_2602;
    private ButtonWidget buttonResetDemo;
    private final Object mutex;
    public static final String OUTDATED_GL_TEXT;
    private int warningTextWidth;
    private int warningTitleWidth;
    private int warningAlignLeft;
    private int warningAlignTop;
    private int warningAlignRight;
    private int warningAlignBottom;
    private String warningTitle;
    private String warningText;
    private String warningLink;
    private static final Identifier MINECRAFT_TITLE_TEXTURE;
    private static final Identifier EDITION_TITLE_TEXTURE;
    private boolean field_2599;
    private Screen realmsNotificationGui;
    private int field_2584;
    private int field_2606;
    private final class_766 field_2585;
    private boolean field_18222;
    private long field_17772;

    public MainMenuScreen() {
        this(false);
    }

    public MainMenuScreen(boolean boolean_1) {
        this.mutex = new Object();
        this.warningText = OUTDATED_GL_TEXT;
        this.field_2585 = new class_766(field_17774);
        this.field_18222 = boolean_1;
        this.field_17776 = (double)(new Random()).nextFloat() < 1.0E-4D;
        this.warningTitle = "";
        if (!GLX.supportsOpenGL2() && !GLX.isNextGen()) {
            this.warningTitle = I18n.translate("title.oldgl1");
            this.warningText = I18n.translate("title.oldgl2");
            this.warningLink = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }

    }

    private boolean areRealmsNotificationsEnabled() {
        return this.client.options.realmsNotifications && this.realmsNotificationGui != null;
    }

    public void update() {
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotificationGui.update();
        }

    }

    public static CompletableFuture<Void> method_18105(TextureManager textureManager_1, Executor executor_1) {
        return CompletableFuture.allOf(textureManager_1.method_18168(MINECRAFT_TITLE_TEXTURE, executor_1), textureManager_1.method_18168(EDITION_TITLE_TEXTURE, executor_1), textureManager_1.method_18168(field_17775, executor_1), field_17774.method_18143(textureManager_1, executor_1));
    }

    public boolean isPauseScreen() {
        return false;
    }

    public boolean doesEscapeKeyClose() {
        return false;
    }

    protected void onInitialized() {
        this.splashText = this.client.getSplashTextLoader().get();
        this.field_2584 = this.fontRenderer.getStringWidth("Copyright Mojang AB. Do not distribute!");
        this.field_2606 = this.screenWidth - this.field_2584 - 2;
        int int_2 = this.screenHeight / 4 + 48;
        if (this.client.isDemo()) {
            this.initWidgetsDemo(int_2, 24);
        } else {
            this.initWidgetsNormal(int_2, 24);
        }

        this.field_2602 = this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, int_2 + 72 + 12, 98, 20, I18n.translate("menu.options", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.client.openScreen(new SettingsScreen(MainMenuScreen.this, MainMenuScreen.this.client.options));
            }
        });
        this.addButton(new ButtonWidget(this.screenWidth / 2 + 2, int_2 + 72 + 12, 98, 20, I18n.translate("menu.quit", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.client.scheduleStop();
            }
        });
        this.addButton(new LanguageButtonWidget(this.screenWidth / 2 - 124, int_2 + 72 + 12) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.client.openScreen(new LanguageSettingsScreen(MainMenuScreen.this, MainMenuScreen.this.client.options, MainMenuScreen.this.client.getLanguageManager()));
            }
        });
        synchronized(this.mutex) {
            this.warningTitleWidth = this.fontRenderer.getStringWidth(this.warningTitle);
            this.warningTextWidth = this.fontRenderer.getStringWidth(this.warningText);
            int int_3 = Math.max(this.warningTitleWidth, this.warningTextWidth);
            this.warningAlignLeft = (this.screenWidth - int_3) / 2;
            this.warningAlignTop = int_2 - 24;
            this.warningAlignRight = this.warningAlignLeft + int_3;
            this.warningAlignBottom = this.warningAlignTop + 24;
        }

        this.client.setConnectedToRealms(false);
        if (this.client.options.realmsNotifications && !this.field_2599) {
            RealmsBridge realmsBridge_1 = new RealmsBridge();
            this.realmsNotificationGui = realmsBridge_1.getNotificationScreen(this);
            this.field_2599 = true;
        }

        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotificationGui.initialize(this.client, this.screenWidth, this.screenHeight);
        }

    }

    private void initWidgetsNormal(int int_1, int int_2) {
        this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, int_1, I18n.translate("menu.singleplayer", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.client.openScreen(new LevelSelectScreen(MainMenuScreen.this));
            }
        });
        this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, int_1 + int_2 * 1, I18n.translate("menu.multiplayer", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.client.openScreen(new MultiplayerScreen(MainMenuScreen.this));
            }
        });
        this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, int_1 + int_2 * 2, I18n.translate("menu.online", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.switchToRealms();
            }
        });
    }

    private void initWidgetsDemo(int int_1, int int_2) {
        this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, int_1, I18n.translate("menu.playdemo", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                MainMenuScreen.this.client.startIntegratedServer("Demo_World", "Demo_World", MinecraftServer.field_17704);
            }
        });
        this.buttonResetDemo = this.addButton(new ButtonWidget(this.screenWidth / 2 - 100, int_1 + int_2 * 1, I18n.translate("menu.resetdemo", new Object[0])) {
            public void onPressed(double double_1, double double_2) {
                LevelStorage levelStorage_1 = MainMenuScreen.this.client.getLevelStorage();
                LevelProperties levelProperties_1 = levelStorage_1.getLevelProperties("Demo_World");
                if (levelProperties_1 != null) {
                    MainMenuScreen.this.client.openScreen(new YesNoScreen(MainMenuScreen.this, I18n.translate("selectWorld.deleteQuestion"), I18n.translate("selectWorld.deleteWarning", levelProperties_1.getLevelName()), I18n.translate("selectWorld.deleteButton"), I18n.translate("gui.cancel"), 12));
                }

            }
        });
        LevelStorage levelStorage_1 = this.client.getLevelStorage();
        LevelProperties levelProperties_1 = levelStorage_1.getLevelProperties("Demo_World");
        if (levelProperties_1 == null) {
            this.buttonResetDemo.enabled = false;
        }

    }

    private void switchToRealms() {
        RealmsBridge realmsBridge_1 = new RealmsBridge();
        realmsBridge_1.switchToRealms(this);
    }

    public void confirmResult(boolean boolean_1, int int_1) {
        if (boolean_1 && int_1 == 12) {
            LevelStorage levelStorage_1 = this.client.getLevelStorage();
            levelStorage_1.deleteLevel("Demo_World");
            this.client.openScreen(this);
        } else if (int_1 == 12) {
            this.client.openScreen(this);
        } else if (int_1 == 13) {
            if (boolean_1) {
                SystemUtil.getOperatingSystem().open(this.warningLink);
            }

            this.client.openScreen(this);
        }

    }

    public void draw(int int_1, int int_2, float float_1) {
        if (this.field_17772 == 0L && this.field_18222) {
            this.field_17772 = SystemUtil.getMeasuringTimeMs();
        }

        float float_2 = this.field_18222 ? (float)(SystemUtil.getMeasuringTimeMs() - this.field_17772) / 1000.0F : 1.0F;
        drawRect(0, 0, this.screenWidth, this.screenHeight, -1);
        this.field_2585.method_3317(float_1, MathHelper.clamp(float_2, 0.0F, 1.0F));
        int int_4 = this.screenWidth / 2 - 137;
        this.client.getTextureManager().bindTexture(field_17775);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, this.field_18222 ? (float)MathHelper.ceil(MathHelper.clamp(float_2, 0.0F, 1.0F)) : 1.0F);
        drawTexturedRect(0, 0, 0.0F, 0.0F, 16, 128, this.screenWidth, this.screenHeight, 16.0F, 128.0F);
        float float_3 = this.field_18222 ? MathHelper.clamp(float_2 - 1.0F, 0.0F, 1.0F) : 1.0F;
        int int_6 = MathHelper.ceil(float_3 * 255.0F) << 24;
        if ((int_6 & -67108864) != 0) {
            this.client.getTextureManager().bindTexture(MINECRAFT_TITLE_TEXTURE);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, float_3);
            if (this.field_17776) {
                this.drawTexturedRect(int_4 + 0, 30, 0, 0, 99, 44);
                this.drawTexturedRect(int_4 + 99, 30, 129, 0, 27, 44);
                this.drawTexturedRect(int_4 + 99 + 26, 30, 126, 0, 3, 44);
                this.drawTexturedRect(int_4 + 99 + 26 + 3, 30, 99, 0, 26, 44);
                this.drawTexturedRect(int_4 + 155, 30, 0, 45, 155, 44);
            } else {
                this.drawTexturedRect(int_4 + 0, 30, 0, 0, 155, 44);
                this.drawTexturedRect(int_4 + 155, 30, 0, 45, 155, 44);
            }

            this.client.getTextureManager().bindTexture(EDITION_TITLE_TEXTURE);
            drawTexturedRect(int_4 + 88, 67, 0.0F, 0.0F, 98, 14, 128.0F, 16.0F);
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)(this.screenWidth / 2 + 90), 70.0F, 0.0F);
            GlStateManager.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
            float float_4 = 1.8F - MathHelper.abs(MathHelper.sin((float)(SystemUtil.getMeasuringTimeMs() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
            float_4 = float_4 * 100.0F / (float)(this.fontRenderer.getStringWidth(this.splashText) + 32);
            GlStateManager.scalef(float_4, float_4, float_4);
            this.drawStringCentered(this.fontRenderer, this.splashText, 0, -8, 16776960 | int_6);
            GlStateManager.popMatrix();
            String string_1 = "Minecraft " + SharedConstants.getGameVersion().getName();
            if (this.client.isDemo()) {
                string_1 = string_1 + " Demo";
            } else {
                string_1 = string_1 + ("release".equalsIgnoreCase(this.client.getVersionType()) ? "" : "/" + this.client.getVersionType());
            }

            this.drawString(this.fontRenderer, string_1, 2, this.screenHeight - 10, 16777215 | int_6);
            this.drawString(this.fontRenderer, "Copyright Mojang AB. Do not distribute!", this.field_2606, this.screenHeight - 10, 16777215 | int_6);
            if (int_1 > this.field_2606 && int_1 < this.field_2606 + this.field_2584 && int_2 > this.screenHeight - 10 && int_2 < this.screenHeight) {
                drawRect(this.field_2606, this.screenHeight - 1, this.field_2606 + this.field_2584, this.screenHeight, 16777215 | int_6);
            }

            if (this.warningTitle != null && !this.warningTitle.isEmpty()) {
                drawRect(this.warningAlignLeft - 2, this.warningAlignTop - 2, this.warningAlignRight + 2, this.warningAlignBottom - 1, 1428160512);
                this.drawString(this.fontRenderer, this.warningTitle, this.warningAlignLeft, this.warningAlignTop, 16777215 | int_6);
                this.drawString(this.fontRenderer, this.warningText, (this.screenWidth - this.warningTextWidth) / 2, this.warningAlignTop + 12, 16777215 | int_6);
            }

            Iterator var12 = this.buttons.iterator();

            while(var12.hasNext()) {
                ButtonWidget buttonWidget_1 = (ButtonWidget)var12.next();
                buttonWidget_1.setOpacity(float_3);
            }

            super.draw(int_1, int_2, float_1);
            if (this.areRealmsNotificationsEnabled() && float_3 >= 1.0F) {
                this.realmsNotificationGui.draw(int_1, int_2, float_1);
            }

        }
    }

    public boolean mouseClicked(double double_1, double double_2, int int_1) {
        if (super.mouseClicked(double_1, double_2, int_1)) {
            return true;
        } else {
            synchronized(this.mutex) {
                if (!this.warningTitle.isEmpty() && !ChatUtil.isEmpty(this.warningLink) && double_1 >= (double)this.warningAlignLeft && double_1 <= (double)this.warningAlignRight && double_2 >= (double)this.warningAlignTop && double_2 <= (double)this.warningAlignBottom) {
                    ConfirmChatLinkScreen confirmChatLinkScreen_1 = new ConfirmChatLinkScreen(this, this.warningLink, 13, true);
                    this.client.openScreen(confirmChatLinkScreen_1);
                    return true;
                }
            }

            if (this.areRealmsNotificationsEnabled() && this.realmsNotificationGui.mouseClicked(double_1, double_2, int_1)) {
                return true;
            } else {
                if (double_1 > (double)this.field_2606 && double_1 < (double)(this.field_2606 + this.field_2584) && double_2 > (double)(this.screenHeight - 10) && double_2 < (double)this.screenHeight) {
                    this.client.openScreen(new EndCreditsScreen(false, Runnables.doNothing()));
                }

                return false;
            }
        }
    }

    public void onClosed() {
        if (this.realmsNotificationGui != null) {
            this.realmsNotificationGui.onClosed();
        }

    }

    static {
        OUTDATED_GL_TEXT = "Please click " + TextFormat.UNDERLINE + "here" + TextFormat.RESET + " for more information.";
        MINECRAFT_TITLE_TEXTURE = new Identifier(Neutronia.MOD_ID, "textures/minecraft_replacement.png");
        EDITION_TITLE_TEXTURE = new Identifier("textures/gui/title/edition.png");
    }
}
