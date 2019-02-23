package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Tree Spinner - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelTreeSpinner extends EntityModel {
    public Cuboid Propeller;
    public Cuboid Body3;
    public Cuboid Head;
    public Cuboid Propellerpart1;
    public Cuboid PropellerPart2;
    public Cuboid PropellerPart3;
    public Cuboid PropellerPart4;
    public Cuboid Body1;
    public Cuboid Body4;
    public Cuboid Body2;
    public Cuboid RightFoot;
    public Cuboid LeftFoot;

    public ModelTreeSpinner() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Body3 = new Cuboid(this, 40, 15);
        this.Body3.setRotationPoint(0.0F, 20.0F, -2.0F);
        this.Body3.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F);
        this.Propellerpart1 = new Cuboid(this, -4, 10);
        this.Propellerpart1.setRotationPoint(0.0F, 14.65F, -9.0F);
        this.Propellerpart1.addBox(-2.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.Body4 = new Cuboid(this, 46, 7);
        this.Body4.setRotationPoint(0.0F, 1.0F, 4.0F);
        this.Body4.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 6, 0.0F);
        this.PropellerPart3 = new Cuboid(this, -4, 10);
        this.PropellerPart3.setRotationPoint(-9.0F, 14.65F, 0.0F);
        this.PropellerPart3.addBox(-2.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.LeftFoot = new Cuboid(this, -1, 0);
        this.LeftFoot.setRotationPoint(1.5F, 2.0F, -2.0F);
        this.LeftFoot.addBox(0.0F, 0.0F, -0.5F, 1, 0, 1, 0.0F);
        this.Body2 = new Cuboid(this, 44, 0);
        this.Body2.setRotationPoint(0.0F, 1.0F, 1.0F);
        this.Body2.addBox(-2.0F, -1.0F, -3.0F, 4, 1, 6, 0.0F);
        this.PropellerPart2 = new Cuboid(this, -4, 10);
        this.PropellerPart2.setRotationPoint(0.0F, 14.65F, 9.0F);
        this.PropellerPart2.addBox(-2.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.Propeller = new Cuboid(this, -18, 14);
        this.Propeller.setRotationPoint(0.0F, 14.75F, 0.0F);
        this.Propeller.addBox(-9.0F, 0.0F, -9.0F, 18, 0, 18, 0.0F);
        this.RightFoot = new Cuboid(this, -1, 0);
        this.RightFoot.setRotationPoint(-1.5F, 2.0F, -2.0F);
        this.RightFoot.addBox(-1.0F, 0.0F, -0.5F, 1, 0, 1, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 21.0F, -1.0F);
        this.Head.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 2, 0.0F);
        this.PropellerPart4 = new Cuboid(this, -4, 10);
        this.PropellerPart4.setRotationPoint(9.0F, 14.65F, 0.0F);
        this.PropellerPart4.addBox(-2.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.Body1 = new Cuboid(this, 30, 0);
        this.Body1.setRotationPoint(0.0F, -3.0F, 2.0F);
        this.Body1.addBox(-2.0F, -2.0F, -2.0F, 4, 2, 4, 0.0F);
        this.Body3.addChild(this.Body4);
        this.Body4.addChild(this.LeftFoot);
        this.Body1.addChild(this.Body2);
        this.Body4.addChild(this.RightFoot);
        this.Body3.addChild(this.Body1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body3.render(f5);
        this.Propellerpart1.render(f5);
        this.PropellerPart3.render(f5);
        this.PropellerPart2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Propeller.offsetX, this.Propeller.offsetY, this.Propeller.offsetZ);
        GlStateManager.translate(this.Propeller.rotationPointX * f5, this.Propeller.rotationPointY * f5, this.Propeller.rotationPointZ * f5);
        GlStateManager.scale(0.85D, 0.85D, 0.85D);
        GlStateManager.translate(-this.Propeller.offsetX, -this.Propeller.offsetY, -this.Propeller.offsetZ);
        GlStateManager.translate(-this.Propeller.rotationPointX * f5, -this.Propeller.rotationPointY * f5, -this.Propeller.rotationPointZ * f5);
        this.Propeller.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GlStateManager.translate(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scale(1.5D, 1.5D, 1.5D);
        GlStateManager.translate(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GlStateManager.translate(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        this.PropellerPart4.render(f5);
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
