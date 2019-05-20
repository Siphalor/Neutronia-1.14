/*
package team.hollow.neutronia.client.entity.render.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

*/
/**
 * ModelGuardHat - Undefined
 * Created using Tabula 7.0.0
 *//*

public class ModelGuardHat extends EntityModel {
    public Cuboid Hat1;
    public Cuboid Hat2;
    public Cuboid Hat3;

    public ModelGuardHat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Hat1 = new Cuboid(this, 0, 0);
        this.Hat1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat1.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Hat3 = new Cuboid(this, -16, 16);
        this.Hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat3.addBox(-8.0F, -3.0F, -8.0F, 16, 0, 16, 0.0F);
        this.Hat2 = new Cuboid(this, 32, 0);
        this.Hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat2.addBox(-4.0F, -8.14F, -4.0F, 8, 8, 8, 0.5F);
        this.Hat1.addChild(Hat2);
        this.Hat1.addChild(Hat3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        if (entity.isSneaking()) {
            GlStateManager.translatef(0.0F, 0.2F, 0.0F);
        }
        this.Hat1.render(scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        setAngle(Hat1, this.bipedHead);
        if (entityIn instanceof EntityArmorStand) {
            EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;
            this.Hat1.rotateAngleX = ((float) Math.PI / 180F) * entityarmorstand.getHeadRotation().getX();
            this.Hat1.rotateAngleY = ((float) Math.PI / 180F) * entityarmorstand.getHeadRotation().getY();
            this.Hat1.rotateAngleZ = ((float) Math.PI / 180F) * entityarmorstand.getHeadRotation().getZ();
        }
    }

    protected void setAngle(Cuboid modelRenderer, Cuboid modelRenderer2) {
        modelRenderer.rotationPointX = modelRenderer2.rotationPointX;
        modelRenderer.rotationPointY = modelRenderer2.rotationPointY;
        modelRenderer.rotationPointZ = modelRenderer2.rotationPointZ;

        modelRenderer.rotateAngleX = modelRenderer2.rotateAngleX;
        modelRenderer.rotateAngleY = modelRenderer2.rotateAngleY;
        modelRenderer.rotateAngleZ = modelRenderer2.rotateAngleZ;
    }

    @Override
    public void setModelAttributes(Model model) {
        super.setModelAttributes(model);

        if (model instanceof ModelBiped) {
            ModelBiped modelbiped = (ModelBiped) model;

            this.leftArmPose = modelbiped.leftArmPose;
            this.rightArmPose = modelbiped.rightArmPose;
            this.isSneak = modelbiped.isSneak;
            this.isChild = modelbiped.isChild;
            this.isRiding = modelbiped.isRiding;
            this.swingProgress = modelbiped.swingProgress;
        }
    }

    */
/**
     * This is a helper function from Tabula to set the rotation of model parts
     *//*

    public void setRotateAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
    }
}*/
