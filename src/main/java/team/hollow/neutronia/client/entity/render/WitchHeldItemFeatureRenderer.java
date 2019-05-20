package team.hollow.neutronia.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import team.hollow.neutronia.client.entity.render.model.WitchModel;

@Environment(EnvType.CLIENT)
public class WitchHeldItemFeatureRenderer<T extends LivingEntity> extends FeatureRenderer<T, WitchModel<T>> {
   public WitchHeldItemFeatureRenderer(FeatureRendererContext<T, WitchModel<T>> featureRendererContext_1) {
      super(featureRendererContext_1);
   }

   public void render(T livingEntity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6, float float_7) {
      ItemStack itemStack_1 = livingEntity_1.getMainHandStack();
      if (!itemStack_1.isEmpty()) {
         GlStateManager.color3f(1.0F, 1.0F, 1.0F);
         GlStateManager.pushMatrix();
         if (((WitchModel)this.getModel()).isChild) {
            GlStateManager.translatef(0.0F, 0.625F, 0.0F);
            GlStateManager.rotatef(-20.0F, -1.0F, 0.0F, 0.0F);
            float float_8 = 0.5F;
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
         }

         ((WitchModel)this.getModel()).method_2839().applyTransform(0.0625F);
         GlStateManager.translatef(-0.0625F, 0.53125F, 0.21875F);
         Item item_1 = itemStack_1.getItem();
         float float_10;
         if (Block.getBlockFromItem(item_1).getDefaultState().getRenderType() == BlockRenderType.ENTITYBLOCK_ANIMATED) {
            GlStateManager.translatef(0.0F, 0.0625F, -0.25F);
            GlStateManager.rotatef(30.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(-5.0F, 0.0F, 1.0F, 0.0F);
            float_10 = 0.375F;
            GlStateManager.scalef(0.375F, -0.375F, 0.375F);
         } else if (item_1 == Items.BOW) {
            GlStateManager.translatef(0.0F, 0.125F, -0.125F);
            GlStateManager.rotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            float_10 = 0.625F;
            GlStateManager.scalef(0.625F, -0.625F, 0.625F);
            GlStateManager.rotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         } else {
            GlStateManager.translatef(0.1875F, 0.1875F, 0.0F);
            float_10 = 0.875F;
            GlStateManager.scalef(0.875F, 0.875F, 0.875F);
            GlStateManager.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotatef(-60.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(-30.0F, 0.0F, 0.0F, 1.0F);
         }

         GlStateManager.rotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotatef(40.0F, 0.0F, 0.0F, 1.0F);
         MinecraftClient.getInstance().getFirstPersonRenderer().renderItem(livingEntity_1, itemStack_1, ModelTransformation.Type.THIRD_PERSON_RIGHT_HAND);
         GlStateManager.popMatrix();
      }
   }

   public boolean hasHurtOverlay() {
      return false;
   }
}
