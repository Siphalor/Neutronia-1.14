package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * axodile - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelAxodile extends EntityModel {
    public Cuboid body;
    public Cuboid shell01;
    public Cuboid shell02;
    public Cuboid lowerJaw;
    public Cuboid lFin;
    public Cuboid rFin;
    public Cuboid head;
    public Cuboid tail01;
    public Cuboid upperBeak;
    public Cuboid tail02;
    public Cuboid tail03;
    public Cuboid tail04;
    public Cuboid tail05;
    public Cuboid tail06;
    public Cuboid lowerJawBeak;

    public ModelAxodile() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.tail03 = new Cuboid(this, 100, 10);
        this.tail03.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tail03.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 6, 0.0F);
        this.tail04 = new Cuboid(this, 100, 10);
        this.tail04.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tail04.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 6, 0.0F);
        this.tail06 = new Cuboid(this, 100, 40);
        this.tail06.setRotationPoint(0.0F, 0.0F, 5.0F);
        this.tail06.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 5, 0.0F);
        this.lowerJawBeak = new Cuboid(this, 67, 48);
        this.lowerJawBeak.setRotationPoint(0.0F, 0.0F, -3.5F);
        this.lowerJawBeak.addBox(-3.5F, -3.0F, -4.5F, 7, 3, 8, 0.0F);
        this.body = new Cuboid(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -4.0F, 8, 3, 10, 0.0F);
        this.tail05 = new Cuboid(this, 100, 27);
        this.tail05.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tail05.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 5, 0.0F);
        this.shell01 = new Cuboid(this, 0, 14);
        this.shell01.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shell01.addBox(-4.0F, -10.0F, -6.1F, 8, 10, 14, 0.0F);
        this.lFin = new Cuboid(this, 28, 0);
        this.lFin.setRotationPoint(3.7F, 0.0F, 0.0F);
        this.lFin.addBox(0.0F, -1.0F, -3.0F, 14, 2, 6, 0.0F);
        this.rFin = new Cuboid(this, 28, 0);
        this.rFin.mirror = true;
        this.rFin.setRotationPoint(-3.7F, 0.0F, 0.0F);
        this.rFin.addBox(-14.0F, -1.0F, -3.0F, 14, 2, 6, 0.0F);
        this.tail02 = new Cuboid(this, 100, 10);
        this.tail02.setRotationPoint(0.0F, 0.0F, 6.0F);
        this.tail02.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 6, 0.0F);
        this.lowerJaw = new Cuboid(this, 67, 18);
        this.lowerJaw.setRotationPoint(0.0F, -0.5F, -4.7F);
        this.lowerJaw.addBox(-3.5F, 0.0F, -8.0F, 7, 1, 8, 0.0F);
        this.head = new Cuboid(this, 68, 0);
        this.head.setRotationPoint(0.0F, -4.7F, -5.8F);
        this.head.addBox(-3.5F, -3.5F, -8.0F, 7, 5, 8, 0.0F);
        this.shell02 = new Cuboid(this, 0, 42);
        this.shell02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shell02.addBox(-6.0F, -7.0F, -4.1F, 12, 7, 10, 0.0F);
        this.tail01 = new Cuboid(this, 100, 10);
        this.tail01.setRotationPoint(0.0F, 0.0F, 5.9F);
        this.tail01.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 6, 0.0F);
        this.upperBeak = new Cuboid(this, 68, 33);
        this.upperBeak.setRotationPoint(0.0F, 1.5F, -4.0F);
        this.upperBeak.addBox(-3.5F, 0.0F, -4.0F, 7, 3, 8, 0.0F);
        this.tail02.addChild(this.tail03);
        this.tail03.addChild(this.tail04);
        this.tail05.addChild(this.tail06);
        this.lowerJaw.addChild(this.lowerJawBeak);
        this.tail04.addChild(this.tail05);
        this.body.addChild(this.lFin);
        this.body.addChild(this.rFin);
        this.tail01.addChild(this.tail02);
        this.body.addChild(this.head);
        this.body.addChild(this.tail01);
        this.head.addChild(this.upperBeak);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
        this.shell01.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.lowerJaw.offsetX, this.lowerJaw.offsetY, this.lowerJaw.offsetZ);
        GlStateManager.translate(this.lowerJaw.rotationPointX * f5, this.lowerJaw.rotationPointY * f5, this.lowerJaw.rotationPointZ * f5);
        GlStateManager.scale(0.9D, 1.0D, 1.0D);
        GlStateManager.translate(-this.lowerJaw.offsetX, -this.lowerJaw.offsetY, -this.lowerJaw.offsetZ);
        GlStateManager.translate(-this.lowerJaw.rotationPointX * f5, -this.lowerJaw.rotationPointY * f5, -this.lowerJaw.rotationPointZ * f5);
        this.lowerJaw.render(f5);
        GlStateManager.popMatrix();
        this.shell02.render(f5);
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
