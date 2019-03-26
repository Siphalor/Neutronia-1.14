package team.hollow.neutronia.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.SignBlockEntityModel;
import net.minecraft.client.util.TextComponentUtil;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.TextComponent;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.blocks.entity.SignBlockEntity;

import java.util.List;

@Environment(EnvType.CLIENT)
public class SignBlockEntityRenderer extends BlockEntityRenderer<SignBlockEntity> {
    private static final Identifier OAK_TEX = new Identifier("neutronia", "textures/entity/signs/bamboo.png");
    private final SignBlockEntityModel model = new SignBlockEntityModel();

    @Override
    public void render(SignBlockEntity signBlockEntity_1, double double_1, double double_2, double double_3, float float_1, int int_1) {
        BlockState blockState_1 = signBlockEntity_1.getCachedState();
        GlStateManager.pushMatrix();
        if (blockState_1.getBlock().matches(BlockTags.STANDING_SIGNS)) {
            GlStateManager.translatef((float) double_1 + 0.5F, (float) double_2 + 0.5F, (float) double_3 + 0.5F);
            GlStateManager.rotatef(-((float) (blockState_1.get(SignBlock.ROTATION) * 360) / 16.0F), 0.0F, 1.0F, 0.0F);
            this.model.getSignpostModel().visible = true;
        } else {
            GlStateManager.translatef((float) double_1 + 0.5F, (float) double_2 + 0.5F, (float) double_3 + 0.5F);
            GlStateManager.rotatef(-blockState_1.get(WallSignBlock.FACING).asRotation(), 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(0.0F, -0.3125F, -0.4375F);
            this.model.getSignpostModel().visible = false;
        }

        if (int_1 >= 0) {
            this.bindTexture(DESTROY_STAGE_TEXTURES[int_1]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(4.0F, 2.0F, 1.0F);
            GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(OAK_TEX);
        }

        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();
        GlStateManager.scalef(0.6666667F, -0.6666667F, -0.6666667F);
        this.model.render();
        GlStateManager.popMatrix();
        TextRenderer fontRenderer_1 = this.getFontRenderer();
        GlStateManager.translatef(0.0F, 0.33333334F, 0.046666667F);
        GlStateManager.scalef(0.010416667F, -0.010416667F, 0.010416667F);
        GlStateManager.normal3f(0.0F, 0.0F, -0.010416667F);
        GlStateManager.depthMask(false);
        int int_2 = signBlockEntity_1.getTextColor().method_16357();
        if (int_1 < 0) {
            for (int int_3 = 0; int_3 < 4; ++int_3) {
                String string_1 = signBlockEntity_1.getTextBeingEditedOnRow(int_3, (textComponent_1) -> {
                    List<TextComponent> list_1 = TextComponentUtil.wrapLines(textComponent_1, 90, fontRenderer_1, false, true);
                    return list_1.isEmpty() ? "" : list_1.get(0).getFormattedText();
                });
                if (string_1 != null) {
                    int i = int_3 * 10 - signBlockEntity_1.text.length * 5;
                    fontRenderer_1.draw(string_1, (float) (-fontRenderer_1.getStringWidth(string_1) / 2), (float) (i), int_2);
                    if (int_3 == signBlockEntity_1.getCurrentRow() && signBlockEntity_1.getSelectionStart() >= 0) {
                        int int_4 = fontRenderer_1.getStringWidth(string_1.substring(0, Math.max(Math.min(signBlockEntity_1.getSelectionStart(), string_1.length()), 0)));
                        int int_5 = fontRenderer_1.isRightToLeft() ? -1 : 1;
                        int int_6 = (int_4 - fontRenderer_1.getStringWidth(string_1) / 2) * int_5;
                        int var10001;
                        if (signBlockEntity_1.isCaretVisible()) {
                            if (signBlockEntity_1.getSelectionStart() < string_1.length()) {
                                var10001 = i - 1;
                                int var10002 = int_6 + 1;
                                DrawableHelper.fill(int_6, var10001, var10002, i + 9, -16777216 | int_2);
                            } else {
                                fontRenderer_1.draw("_", (float) int_6, (float) i, int_2);
                            }
                        }

                        if (signBlockEntity_1.getSelectionEnd() != signBlockEntity_1.getSelectionStart()) {
                            int int_8 = Math.min(signBlockEntity_1.getSelectionStart(), signBlockEntity_1.getSelectionEnd());
                            int int_9 = Math.max(signBlockEntity_1.getSelectionStart(), signBlockEntity_1.getSelectionEnd());
                            int int_10 = (fontRenderer_1.getStringWidth(string_1.substring(0, int_8)) - fontRenderer_1.getStringWidth(string_1) / 2) * int_5;
                            int int_11 = (fontRenderer_1.getStringWidth(string_1.substring(0, int_9)) - fontRenderer_1.getStringWidth(string_1) / 2) * int_5;
                            var10001 = Math.min(int_10, int_11);
                            int var10003 = Math.max(int_10, int_11);
                            this.method_16210(var10001, i, var10003, i + 9);
                        }
                    }
                }
            }
        }

        GlStateManager.depthMask(true);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
        if (int_1 >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }

    }

    private void method_16210(int int_1, int int_2, int int_3, int int_4) {
        Tessellator tessellator_1 = Tessellator.getInstance();
        BufferBuilder bufferBuilder_1 = tessellator_1.getBufferBuilder();
        GlStateManager.color4f(0.0F, 0.0F, 255.0F, 255.0F);
        GlStateManager.disableTexture();
        GlStateManager.enableColorLogicOp();
        GlStateManager.logicOp(GlStateManager.LogicOp.OR_REVERSE);
        bufferBuilder_1.begin(7, VertexFormats.POSITION);
        bufferBuilder_1.vertex((double) int_1, (double) int_4, 0.0D).next();
        bufferBuilder_1.vertex((double) int_3, (double) int_4, 0.0D).next();
        bufferBuilder_1.vertex((double) int_3, (double) int_2, 0.0D).next();
        bufferBuilder_1.vertex((double) int_1, (double) int_2, 0.0D).next();
        tessellator_1.draw();
        GlStateManager.disableColorLogicOp();
        GlStateManager.enableTexture();
    }
}
