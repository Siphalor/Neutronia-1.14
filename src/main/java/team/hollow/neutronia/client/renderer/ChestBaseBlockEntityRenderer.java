package team.hollow.neutronia.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.block.ChestAnimationProgress;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import team.hollow.neutronia.blocks.entity.ChestBaseBlockEntity;
import team.hollow.neutronia.client.entity.render.model.BaseChestModel;

public class ChestBaseBlockEntityRenderer extends BlockEntityRenderer<ChestBaseBlockEntity> {

    private final BaseChestModel single;
    private final BaseChestModel doubleChest;

    public ChestBaseBlockEntityRenderer(BaseChestModel single, BaseChestModel doubleChest) {
        this.single = single;
        this.doubleChest = doubleChest;
    }

    @Override
    public void render(ChestBaseBlockEntity chestBaseBlockEntity, double var2, double var4, double var6, float var8, int var9) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        BlockState blockState = chestBaseBlockEntity.hasWorld() ? chestBaseBlockEntity.getCachedState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = blockState.contains(ChestBlock.CHEST_TYPE) ? blockState.get(ChestBlock.CHEST_TYPE) : ChestType.SINGLE;
        if (chestType != ChestType.LEFT) {
            boolean var12 = chestType != ChestType.SINGLE;
            BaseChestModel chestEntityModel = this.getTexture(chestBaseBlockEntity, var9, var12);
            if (var9 >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(var12 ? 8.0F : 4.0F, 4.0F, 1.0F);
                GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
                GlStateManager.matrixMode(5888);
            } else {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translatef((float) var2, (float) var4 + 1.0F, (float) var6 + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            float rotation = blockState.get(ChestBlock.FACING).asRotation();
            if ((double) Math.abs(rotation) > 1.0E-5D) {
                GlStateManager.translatef(0.5F, 0.5F, 0.5F);
                GlStateManager.rotatef(rotation, 0.0F, 1.0F, 0.0F);
                GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            }

            this.animate(chestBaseBlockEntity, var8, chestEntityModel);
            chestEntityModel.render();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (var9 >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }

        }
    }

    private BaseChestModel getTexture(ChestBaseBlockEntity var1, int var2, boolean var3) {
        Identifier texture;
        if (var2 >= 0) {
            texture = DESTROY_STAGE_TEXTURES[var2];
        } else {
            texture = var3 ? var1.baseBlock.getDoubleModelTexture() : var1.baseBlock.getModelTexture();
        }
        this.bindTexture(texture);
        return var3 ? this.single : this.doubleChest;
    }

    private void animate(ChestBaseBlockEntity chestBaseBlockEntity, float var2, BaseChestModel chestEntityModel) {
        float animationProgress = ((ChestAnimationProgress) chestBaseBlockEntity).getAnimationProgress(var2);
        animationProgress = 1.0F - animationProgress;
        animationProgress = 1.0F - animationProgress * animationProgress * animationProgress;
        chestEntityModel.getLid().pitch = -(animationProgress * 1.5707964F);
    }
}