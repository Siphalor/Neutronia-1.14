package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelShroomGlutton - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelShroomGlutton extends EntityModel {
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Mushroom1;
    public Cuboid Mushroom2;
    public Cuboid Mushroom3;
    public Cuboid Mushroom4;
    public Cuboid Mushroom5;
    public Cuboid Mushroom6;
    public Cuboid Mushroom7;
    public Cuboid Mushroom8;

    public ModelShroomGlutton() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new Cuboid(this, 40, 16);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Mushroom3 = new Cuboid(this, 36, 16);
        this.Mushroom3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom3.addBox(-4.0F, -5.0F, -1.0F, 3, 3, 1, 0.0F);
        this.Mushroom6 = new Cuboid(this, 45, 1);
        this.Mushroom6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom6.addBox(1.0F, -5.0F, -3.0F, 1, 3, 3, 0.0F);
        this.Body = new Cuboid(this, 16, 16);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.Mushroom2 = new Cuboid(this, 32, 1);
        this.Mushroom2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom2.addBox(-2.5F, -14.0F, -6.0F, 1, 6, 6, 0.0F);
        this.Mushroom4 = new Cuboid(this, 46, 10);
        this.Mushroom4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom4.addBox(-2.5F, -5.0F, -2.5F, 1, 3, 3, 0.0F);
        this.Mushroom5 = new Cuboid(this, 40, 0);
        this.Mushroom5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom5.addBox(-0.5F, -5.0F, -1.5F, 3, 3, 1, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Mushroom8 = new Cuboid(this, 54, 5);
        this.Mushroom8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom8.addBox(2.0F, -6.0F, -1.0F, 1, 4, 4, 0.0F);
        this.RightArm = new Cuboid(this, 40, 16);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 16);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 32);
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Mushroom7 = new Cuboid(this, 54, 0);
        this.Mushroom7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom7.addBox(0.0F, -6.0F, 1.0F, 4, 4, 1, 0.0F);
        this.Mushroom1 = new Cuboid(this, 24, 0);
        this.Mushroom1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mushroom1.addBox(-5.5F, -14.0F, -3.0F, 6, 6, 1, 0.0F);
        this.RightArm.addChild(this.Mushroom3);
        this.LeftArm.addChild(this.Mushroom6);
        this.Head.addChild(this.Mushroom2);
        this.RightArm.addChild(this.Mushroom4);
        this.LeftArm.addChild(this.Mushroom5);
        this.LeftArm.addChild(this.Mushroom8);
        this.LeftArm.addChild(this.Mushroom7);
        this.Head.addChild(this.Mushroom1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftArm.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.RightArm.render(f5);
        this.RightLeg.render(f5);
        this.LeftLeg.render(f5);
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
