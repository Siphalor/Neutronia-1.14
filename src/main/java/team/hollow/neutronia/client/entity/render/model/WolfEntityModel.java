package team.hollow.neutronia.client.entity.render.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import team.hollow.neutronia.entity.ArcticWolfEntity;

@Environment(EnvType.CLIENT)
public class WolfEntityModel<T extends ArcticWolfEntity> extends EntityModel<T> {
   private final Cuboid field_3621;
   private final Cuboid field_3623;
   private final Cuboid field_3622;
   private final Cuboid field_3620;
   private final Cuboid field_3618;
   private final Cuboid field_3624;
   private final Cuboid field_3617;
   private final Cuboid field_3619;

   public WolfEntityModel() {
      float float_1 = 0.0F;
      float float_2 = 13.5F;
      this.field_3621 = new Cuboid(this, 0, 0);
      this.field_3621.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
      this.field_3621.setRotationPoint(-1.0F, 13.5F, -7.0F);
      this.field_3623 = new Cuboid(this, 18, 14);
      this.field_3623.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F);
      this.field_3623.setRotationPoint(0.0F, 14.0F, 2.0F);
      this.field_3619 = new Cuboid(this, 21, 0);
      this.field_3619.addBox(-3.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F);
      this.field_3619.setRotationPoint(-1.0F, 14.0F, 2.0F);
      this.field_3622 = new Cuboid(this, 0, 18);
      this.field_3622.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
      this.field_3622.setRotationPoint(-2.5F, 16.0F, 7.0F);
      this.field_3620 = new Cuboid(this, 0, 18);
      this.field_3620.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
      this.field_3620.setRotationPoint(0.5F, 16.0F, 7.0F);
      this.field_3618 = new Cuboid(this, 0, 18);
      this.field_3618.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
      this.field_3618.setRotationPoint(-2.5F, 16.0F, -4.0F);
      this.field_3624 = new Cuboid(this, 0, 18);
      this.field_3624.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
      this.field_3624.setRotationPoint(0.5F, 16.0F, -4.0F);
      this.field_3617 = new Cuboid(this, 9, 18);
      this.field_3617.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
      this.field_3617.setRotationPoint(-1.0F, 12.0F, 8.0F);
      this.field_3621.setTextureOffset(16, 14).addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
      this.field_3621.setTextureOffset(16, 14).addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
      this.field_3621.setTextureOffset(0, 10).addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
   }

   public void render(T wolfEntity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
      super.render(wolfEntity_1, float_1, float_2, float_3, float_4, float_5, float_6);
      this.setAngles(wolfEntity_1, float_1, float_2, float_3, float_4, float_5, float_6);
      if (this.isChild) {
         float float_7 = 2.0F;
         GlStateManager.pushMatrix();
         GlStateManager.translatef(0.0F, 5.0F * float_6, 2.0F * float_6);
         this.field_3621.method_2852(float_6);
         GlStateManager.popMatrix();
         GlStateManager.pushMatrix();
         GlStateManager.scalef(0.5F, 0.5F, 0.5F);
         GlStateManager.translatef(0.0F, 24.0F * float_6, 0.0F);
         this.field_3623.render(float_6);
         this.field_3622.render(float_6);
         this.field_3620.render(float_6);
         this.field_3618.render(float_6);
         this.field_3624.render(float_6);
         this.field_3617.method_2852(float_6);
         this.field_3619.render(float_6);
         GlStateManager.popMatrix();
      } else {
         this.field_3621.method_2852(float_6);
         this.field_3623.render(float_6);
         this.field_3622.render(float_6);
         this.field_3620.render(float_6);
         this.field_3618.render(float_6);
         this.field_3624.render(float_6);
         this.field_3617.method_2852(float_6);
         this.field_3619.render(float_6);
      }

   }

   public void animateModel(T wolfEntity_1, float float_1, float float_2, float float_3) {
      if (wolfEntity_1.isAngry()) {
         this.field_3617.yaw = 0.0F;
      } else {
         this.field_3617.yaw = MathHelper.cos(float_1 * 0.6662F) * 1.4F * float_2;
      }

      if (wolfEntity_1.isSitting()) {
         this.field_3619.setRotationPoint(-1.0F, 16.0F, -3.0F);
         this.field_3619.pitch = 1.2566371F;
         this.field_3619.yaw = 0.0F;
         this.field_3623.setRotationPoint(0.0F, 18.0F, 0.0F);
         this.field_3623.pitch = 0.7853982F;
         this.field_3617.setRotationPoint(-1.0F, 21.0F, 6.0F);
         this.field_3622.setRotationPoint(-2.5F, 22.0F, 2.0F);
         this.field_3622.pitch = 4.712389F;
         this.field_3620.setRotationPoint(0.5F, 22.0F, 2.0F);
         this.field_3620.pitch = 4.712389F;
         this.field_3618.pitch = 5.811947F;
         this.field_3618.setRotationPoint(-2.49F, 17.0F, -4.0F);
         this.field_3624.pitch = 5.811947F;
         this.field_3624.setRotationPoint(0.51F, 17.0F, -4.0F);
      } else {
         this.field_3623.setRotationPoint(0.0F, 14.0F, 2.0F);
         this.field_3623.pitch = 1.5707964F;
         this.field_3619.setRotationPoint(-1.0F, 14.0F, -3.0F);
         this.field_3619.pitch = this.field_3623.pitch;
         this.field_3617.setRotationPoint(-1.0F, 12.0F, 8.0F);
         this.field_3622.setRotationPoint(-2.5F, 16.0F, 7.0F);
         this.field_3620.setRotationPoint(0.5F, 16.0F, 7.0F);
         this.field_3618.setRotationPoint(-2.5F, 16.0F, -4.0F);
         this.field_3624.setRotationPoint(0.5F, 16.0F, -4.0F);
         this.field_3622.pitch = MathHelper.cos(float_1 * 0.6662F) * 1.4F * float_2;
         this.field_3620.pitch = MathHelper.cos(float_1 * 0.6662F + 3.1415927F) * 1.4F * float_2;
         this.field_3618.pitch = MathHelper.cos(float_1 * 0.6662F + 3.1415927F) * 1.4F * float_2;
         this.field_3624.pitch = MathHelper.cos(float_1 * 0.6662F) * 1.4F * float_2;
      }

      this.field_3621.roll = wolfEntity_1.method_6719(float_3) + wolfEntity_1.method_6715(float_3, 0.0F);
      this.field_3619.roll = wolfEntity_1.method_6715(float_3, -0.08F);
      this.field_3623.roll = wolfEntity_1.method_6715(float_3, -0.16F);
      this.field_3617.roll = wolfEntity_1.method_6715(float_3, -0.2F);
   }

   public void setAngles(T wolfEntity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
      super.setAngles(wolfEntity_1, float_1, float_2, float_3, float_4, float_5, float_6);
      this.field_3621.pitch = float_5 * 0.017453292F;
      this.field_3621.yaw = float_4 * 0.017453292F;
      this.field_3617.pitch = float_3;
   }

}
