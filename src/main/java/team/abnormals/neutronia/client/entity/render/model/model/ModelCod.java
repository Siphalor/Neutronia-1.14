package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCod extends EntityModel {

    public Cuboid head0;
    public Cuboid head1;
    public Cuboid leftFin;
    public Cuboid rightFin;
    public Cuboid tailFin;
    public Cuboid waist;
    public Cuboid body0;
    public Cuboid body1;

    public ModelCod() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.leftFin = new Cuboid(this, 24, 4);
        this.leftFin.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.leftFin.addBox(1.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        this.setRotateAngle(leftFin, -0.045553093477052F, 0.0F, 0.0F);
        this.body0 = new Cuboid(this, 0, 0);
        this.body0.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body0.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 7, 0.0F);
        this.head1 = new Cuboid(this, 11, 0);
        this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head1.addBox(-1.0F, 0.0F, -4.0F, 2, 4, 3, 0.0F);
        this.head0 = new Cuboid(this, 0, 0);
        this.head0.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head0.addBox(-1.0F, 1.0F, -5.0F, 2, 3, 1, 0.0F);
        this.tailFin = new Cuboid(this, 20, 1);
        this.tailFin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailFin.addBox(0.0F, 0.0F, 6.0F, 0, 4, 6, 0.0F);
        this.waist = new Cuboid(this, 0, 0);
        this.waist.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.waist.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.body1 = new Cuboid(this, 20, -6);
        this.body1.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.body1.addBox(-0.6F, 4.0F, -2.0F, 0, 0, 6, 0.5F);
        this.rightFin = new Cuboid(this, 24, 1);
        this.rightFin.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.rightFin.addBox(-3.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftFin.offsetX, this.leftFin.offsetY, this.leftFin.offsetZ);
        GlStateManager.translate(this.leftFin.rotationPointX * scale, this.leftFin.rotationPointY * scale, this.leftFin.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 0.0D, 1.0D);
        GlStateManager.translate(-this.leftFin.offsetX, -this.leftFin.offsetY, -this.leftFin.offsetZ);
        GlStateManager.translate(-this.leftFin.rotationPointX * scale, -this.leftFin.rotationPointY * scale, -this.leftFin.rotationPointZ * scale);
        this.leftFin.render(scale);
        GlStateManager.popMatrix();
        this.body0.render(scale);
        this.head1.render(scale);
        this.head0.render(scale);
        this.tailFin.render(scale);
        this.waist.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body1.offsetX, this.body1.offsetY, this.body1.offsetZ);
        GlStateManager.translate(this.body1.rotationPointX * scale, this.body1.rotationPointY * scale, this.body1.rotationPointZ * scale);
        GlStateManager.scale(0.0D, 0.9D, 1.0D);
        GlStateManager.translate(-this.body1.offsetX, -this.body1.offsetY, -this.body1.offsetZ);
        GlStateManager.translate(-this.body1.rotationPointX * scale, -this.body1.rotationPointY * scale, -this.body1.rotationPointZ * scale);
        this.body1.render(scale);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightFin.offsetX, this.rightFin.offsetY, this.rightFin.offsetZ);
        GlStateManager.translate(this.rightFin.rotationPointX * scale, this.rightFin.rotationPointY * scale, this.rightFin.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 0.0D, 1.0D);
        GlStateManager.translate(-this.rightFin.offsetX, -this.rightFin.offsetY, -this.rightFin.offsetZ);
        GlStateManager.translate(-this.rightFin.rotationPointX * scale, -this.rightFin.rotationPointY * scale, -this.rightFin.rotationPointZ * scale);
        this.rightFin.render(scale);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.rotationPointX = x;
        Cuboid.rotationPointY = y;
        Cuboid.rotationPointZ = z;
    }

}
