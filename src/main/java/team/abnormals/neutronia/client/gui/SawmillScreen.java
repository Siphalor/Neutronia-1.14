package team.abnormals.neutronia.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.audio.PositionedSoundInstance;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import team.abnormals.neutronia.container.SawmillContainer;
import team.abnormals.neutronia.recipe.SawmillingRecipe;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SawmillScreen extends ContainerScreen<SawmillContainer> {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/stonecutter.png");
    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;
    private boolean canCraft;

    public SawmillScreen(SawmillContainer stonecutterContainer_1) {
        super(stonecutterContainer_1, stonecutterContainer_1.playerInventory, new TranslatableTextComponent("container.sawmill"));
        stonecutterContainer_1.setContentsChangedListener(this::onInventoryChange);
    }

    public void draw(int int_1, int int_2, float float_1) {
        super.draw(int_1, int_2, float_1);
        this.drawMouseoverTooltip(int_1, int_2);
    }

    protected void drawForeground(int int_1, int int_2) {
        this.fontRenderer.draw(this.name.getFormattedText(), 8.0F, 4.0F, 4210752);
        this.fontRenderer.draw(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.containerHeight - 94), 4210752);
    }

    protected void drawBackground(float float_1, int int_1, int int_2) {
        this.drawBackground();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(TEXTURE);
        int int_3 = this.left;
        int int_4 = this.top;
        this.drawTexturedRect(int_3, int_4, 0, 0, this.containerWidth, this.containerHeight);
        int int_5 = (int) (41.0F * this.scrollAmount);
        this.drawTexturedRect(int_3 + 119, int_4 + 15 + int_5, 176 + (this.shouldScroll() ? 0 : 12), 0, 12, 15);
        int int_6 = this.left + 52;
        int int_7 = this.top + 14;
        int int_8 = this.scrollOffset + 12;
        this.method_17952(int_1, int_2, int_6, int_7, int_8);
        this.method_17951(int_6, int_7, int_8);
    }

    private void method_17952(int int_1, int int_2, int int_3, int int_4, int int_5) {
        for (int int_6 = this.scrollOffset; int_6 < int_5 && int_6 < this.container.getAvailableRecipeCount(); ++int_6) {
            int int_7 = int_6 - this.scrollOffset;
            int int_8 = int_3 + int_7 % 4 * 16;
            int int_9 = int_7 / 4;
            int int_10 = int_4 + int_9 * 18 + 2;
            int int_11 = this.containerHeight;
            if (int_6 == this.container.method_17862()) {
                int_11 += 18;
            } else if (int_1 >= int_8 && int_2 >= int_10 && int_1 < int_8 + 16 && int_2 < int_10 + 18) {
                int_11 += 36;
            }

            this.drawTexturedRect(int_8, int_10 - 1, 0, int_11, 16, 18);
        }

    }

    private void method_17951(int int_1, int int_2, int int_3) {
        GuiLighting.enableForItems();
        List<SawmillingRecipe> list_1 = this.container.getAvailableRecipes();

        for (int int_4 = this.scrollOffset; int_4 < int_3 && int_4 < this.container.getAvailableRecipeCount(); ++int_4) {
            int int_5 = int_4 - this.scrollOffset;
            int int_6 = int_1 + int_5 % 4 * 16;
            int int_7 = int_5 / 4;
            int int_8 = int_2 + int_7 * 18 + 2;
//         this.client.getItemRenderer().renderItemAndGlow(list_1.get(int_4).getOutput(), int_6, int_8);
        }

        GuiLighting.disable();
    }

    public boolean mouseClicked(double double_1, double double_2, int int_1) {
        this.mouseClicked = false;
        if (this.canCraft) {
            int int_2 = this.left + 52;
            int int_3 = this.top + 14;
            int int_4 = this.scrollOffset + 12;

            for (int int_5 = this.scrollOffset; int_5 < int_4; ++int_5) {
                int int_6 = int_5 - this.scrollOffset;
                double double_3 = double_1 - (double) (int_2 + int_6 % 4 * 16);
                double double_4 = double_2 - (double) (int_3 + int_6 / 4 * 18);
                if (double_3 >= 0.0D && double_4 >= 0.0D && double_3 < 16.0D && double_4 < 18.0D && this.container.onButtonClick(this.client.player, int_5)) {
                    MinecraftClient.getInstance().getSoundLoader().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.client.interactionManager.clickButton(this.container.syncId, int_5);
                    return true;
                }
            }

            int_2 = this.left + 119;
            int_3 = this.top + 9;
            if (double_1 >= (double) int_2 && double_1 < (double) (int_2 + 12) && double_2 >= (double) int_3 && double_2 < (double) (int_3 + 54)) {
                this.mouseClicked = true;
            }
        }

        return super.mouseClicked(double_1, double_2, int_1);
    }

    public boolean mouseDragged(double double_1, double double_2, int int_1, double double_3, double double_4) {
        if (this.mouseClicked && this.shouldScroll()) {
            int int_2 = this.top + 14;
            int int_3 = int_2 + 54;
            this.scrollAmount = ((float) double_2 - (float) int_2 - 7.5F) / ((float) (int_3 - int_2) - 15.0F);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int) ((double) (this.scrollAmount * (float) this.getMaxScroll()) + 0.5D) * 4;
            return true;
        } else {
            return super.mouseDragged(double_1, double_2, int_1, double_3, double_4);
        }
    }

    public boolean mouseScrolled(double double_1) {
        if (this.shouldScroll()) {
            int int_1 = this.getMaxScroll();
            this.scrollAmount = (float) ((double) this.scrollAmount - double_1 / (double) int_1);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int) ((double) (this.scrollAmount * (float) int_1) + 0.5D) * 4;
        }

        return true;
    }

    private boolean shouldScroll() {
        return this.canCraft && this.container.getAvailableRecipeCount() > 12;
    }

    protected int getMaxScroll() {
        return (this.container.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    private void onInventoryChange() {
        this.canCraft = this.container.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }

    }
}
