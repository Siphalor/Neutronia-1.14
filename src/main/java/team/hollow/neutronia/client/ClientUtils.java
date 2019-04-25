/*
package team.hollow.neutronia.client;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class ClientUtils {

    public static float sineTicker = 0 + MinecraftClient.getInstance().gameRenderer.;

    public int cycleLoadingScreenFrequency = 0;

    public float frequency = 5F;

    public float scale = 3F;

    public float scaleDeviation = 5.25F;

    public float tiltRange = 11.25F;

    public float tiltConstant = 22.5F;

    public String[] loadingIconStacks = {
            "twilightforest:experiment_115",
            "twilightforest:magic_map",
            "twilightforest:charm_of_life_2",
            "twilightforest:charm_of_keeping_3",
            "twilightforest:phantom_helmet",
            "twilightforest:lamp_of_cinders",
            "twilightforest:carminite",
            "twilightforest:block_and_chain",
            "twilightforest:yeti_helmet",
            "twilightforest:hydra_chop",
            "twilightforest:magic_beans",
            "twilightforest:ironwood_raw",
            "twilightforest:naga_scale",
            "twilightforest:experiment_115:2",
            "twilightforest:miniature_structure",
            "twilightforest:miniature_structure:6",
            "twilightforest:knightmetal_block",
            "twilightforest:tower_device:10",
            "twilightforest:twilight_sapling:5",
            "twilightforest:twilight_sapling:6",
            "twilightforest:twilight_sapling:7",
            "twilightforest:twilight_sapling:8",
            "twilightforest:twilight_sapling:9",
            "twilightforest:borer_essence"
    };

    private ImmutableList<ItemStack> loadingScreenIcons;

    public ImmutableList<ItemStack> getLoadingScreenIcons() {
        return loadingScreenIcons;
    }

    void loadLoadingScreenIcons() {
        ImmutableList.Builder<ItemStack> iconList = ImmutableList.builder();

        for (String s : loadingIconStacks) {
            parseItemStack(s).ifPresent(iconList::add);
        }

        loadingScreenIcons = iconList.build();
    }

    private void drawBouncingWobblyItem(ItemStack item, float partialTicks, float width, float height) {
        float sineTicker = (TFClientEvents.sineTicker + partialTicks) * frequency;
        float sineTicker2 = (TFClientEvents.sineTicker + 314f + partialTicks) * frequency;
        GlStateManager.pushMatrix();

        // Shove it!
        GlStateManager.translatef(width - ((width / 30f) * scale), height - (height / 10f), 0f); // Bottom right Corner

        // Wobble it!
        GlStateManager.rotatef(MathHelper.sin(sineTicker / tiltRange) * tiltConstant, 0f, 0f, 1f);

        // Bounce it!
        GlStateManager.scalef(((MathHelper.sin(((sineTicker2 + 180F) / tiltRange) * 2F) / scaleDeviation) + 2F) * (scale / 2F), ((MathHelper.sin(((sineTicker + 180F) / tiltRange) * 2F) / scaleDeviation) + 2F) * (scale / 2F), 1F);

        // Shift it!
        GlStateManager.translatef(-8f, -16.5f, 0f);

        GuiLighting.enable();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0x20, 0x20);
        // Draw it!
        MinecraftClient.getInstance().getItemRenderer().renderGuiItem(item, 0, 0);
        GuiLighting.disable();

        // Pop it!
        GlStateManager.popMatrix();
        // Bop it!
    }

    private static Optional<ItemStack> parseItemStack(String string) {
        String[] split = string.split(":");
        if (split.length < 2) return Optional.empty();

        Item item = Registry.ITEM.get(new Identifier(split[0], split[1]));
        if (item == Items.AIR) return Optional.empty();

        return Optional.of(new ItemStack(item, 1));
    }

}
*/
