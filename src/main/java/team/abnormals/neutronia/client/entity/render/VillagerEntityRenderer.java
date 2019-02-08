package team.abnormals.neutronia.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.model.VillagerEntityModel;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.util.Identifier;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

@Environment(EnvType.CLIENT)
public class VillagerEntityRenderer extends MobEntityRenderer<VillagerPlusEntity, VillagerEntityModel<VillagerPlusEntity>> {
   private static final Identifier VILLAGER_SKIN = new Identifier("textures/entity/villager/villager.png");

   public VillagerEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1, ReloadableResourceManager reloadableResourceManager_1) {
      super(entityRenderDispatcher_1, new VillagerEntityModel<>(0.0F), 0.5F);
      this.addFeature(new HeadFeatureRenderer<>(this));
//      this.addFeature(new VillagerClothingFeatureRenderer(this, reloadableResourceManager_1, "villager_plus"));
   }

   protected Identifier getTexture(VillagerPlusEntity villagerEntity_1) {
      return VILLAGER_SKIN;
   }

   protected void method_4149(VillagerPlusEntity villagerEntity_1, float float_1) {
      float float_2 = 0.9375F;
      if (villagerEntity_1.isChild()) {
         float_2 = (float)((double)float_2 * 0.5D);
         this.field_4673 = 0.25F;
      } else {
         this.field_4673 = 0.5F;
      }

      GlStateManager.scalef(float_2, float_2, float_2);
   }
}
