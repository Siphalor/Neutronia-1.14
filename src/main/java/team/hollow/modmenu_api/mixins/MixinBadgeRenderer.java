package team.hollow.modmenu_api.mixins;

import io.github.prospector.modmenu.ModMenu;
import io.github.prospector.modmenu.gui.ModListScreen;
import io.github.prospector.modmenu.util.BadgeRenderer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import team.hollow.modmenu_api.ModMenuBadgeManager;
import team.hollow.modmenu_api.api.ModMenuBadge;
import team.hollow.modmenu_api.api.ModMenuBadges;

@Mixin(value = BadgeRenderer.class, remap = false)
public abstract class MixinBadgeRenderer {

    @Shadow public ModMetadata metadata;
    @Shadow protected int badgeX;
    @Shadow protected int badgeY;
    @Shadow protected int badgeMax;
    @Shadow private MinecraftClient client;
    @Shadow protected ModListScreen screen;
    @Shadow protected int startX;
    @Shadow protected int startY;

    /**
     * @author OliviaTheVampire
     */
    @Overwrite
    public void draw(int mouseX, int mouseY) {
        this.badgeX = this.startX;
        this.badgeY = this.startY;
        ModMenuBadgeManager.getBadges().forEach(((s, modMenuBadges) -> {
            if(metadata.getId().equals(s)) {
                renderBadge(modMenuBadges);
            }
        }));
        Boolean clientside = ModMenu.MOD_CLIENTSIDE.get(this.metadata.getId());
        if (clientside != null && clientside) {
            this.renderBadge(ModMenuBadges.CLIENT_SIDE);
        }

        Boolean api = ModMenu.MOD_API.get(this.metadata.getId());
        if (api == null) {
            api = this.metadata.getId().equals("fabricloader") || this.metadata.getId().equals("fabric") || this.metadata.getName().endsWith(" API");
        }

        if (api) {
            this.renderBadge(ModMenuBadges.API);
        }
    }

    private void renderBadge(ModMenuBadge modMenuBadges) {
        int width = client.textRenderer.getStringWidth(modMenuBadges.text.getFormattedText()) + 6;
        if (badgeX + width < badgeMax) {
            drawBadge(badgeX, badgeY, width, modMenuBadges.text, modMenuBadges.outlineColor, modMenuBadges.fillColor);
            badgeX += width + 3;
        }
    }

    private void renderBadge(ModMenuBadge[] modMenuBadges) {
        for(ModMenuBadge badges : modMenuBadges) {
            int width = client.textRenderer.getStringWidth(badges.text.getFormattedText()) + 6;
            if (badgeX + width < badgeMax) {
                drawBadge(badgeX, badgeY, width, badges.text, badges.outlineColor, badges.fillColor);
                badgeX += width + 3;
            }
        }
    }

    private void drawBadge(int x, int y, int tagWidth, Component text, int outlineColor, int fillColor) {
        DrawableHelper.fill(x + 1, y - 1, x + tagWidth, y, outlineColor);
        DrawableHelper.fill(x, y, x + 1, y + client.textRenderer.fontHeight, outlineColor);
        DrawableHelper.fill(x + 1, y + 1 + client.textRenderer.fontHeight - 1, x + tagWidth, y + client.textRenderer.fontHeight + 1, outlineColor);
        DrawableHelper.fill(x + tagWidth, y, x + tagWidth + 1, y + client.textRenderer.fontHeight, outlineColor);
        DrawableHelper.fill(x + 1, y, x + tagWidth, y + client.textRenderer.fontHeight, fillColor);
        client.textRenderer.draw(text.getFormattedText(), (x + 1 + ((tagWidth - client.textRenderer.getStringWidth(text.getFormattedText())) >> 1)), y + 1, 13290186);
    }

}