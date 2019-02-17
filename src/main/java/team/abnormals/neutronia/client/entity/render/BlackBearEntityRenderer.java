package team.abnormals.neutronia.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.client.entity.render.model.BlackBearEntityModel;
import team.abnormals.neutronia.entity.passive.BlackBearEntity;

@Environment(EnvType.CLIENT)
public class BlackBearEntityRenderer extends MobEntityRenderer<BlackBearEntity, BlackBearEntityModel<BlackBearEntity>> {
   private static final Identifier SKIN = new Identifier("textures/entity/bear/blackbear.png");

   public BlackBearEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
      super(entityRenderDispatcher_1, new BlackBearEntityModel<>(), 0.7F);
   }

   protected Identifier getTexture(BlackBearEntity polarBearEntity_1) {
      return SKIN;
   }

   protected void method_4099(BlackBearEntity polarBearEntity_1, float float_1) {
      GlStateManager.scalef(1.2F, 1.2F, 1.2F);
      super.method_4042(polarBearEntity_1, float_1);
   }

}