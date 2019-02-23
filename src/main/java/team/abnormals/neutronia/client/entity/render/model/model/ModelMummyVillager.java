package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Mummy Villager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMummyVillager extends EntityModel {
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid Hat;
    public Cuboid Head;
    public Cuboid Nose;
    public Cuboid Body;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Cloth2;
    public Cuboid Cloth3;
    public Cuboid Cloth1;
    public Cuboid Cloth4;

    public ModelMummyVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RightArm = new Cuboid(this, 44, 22);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightArm, -1.3089969389957472F, 0.08726646259971647F, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Cloth3 = new Cuboid(this, 54, 38);
        this.Cloth3.setRotationPoint(1.0F, 4.0F, 2.0F);
        this.Cloth3.addBox(-1.0F, -6.0F, 0.0F, 2, 12, 3, 0.0F);
        this.Cloth2 = new Cuboid(this, 54, 38);
        this.Cloth2.mirror = true;
        this.Cloth2.setRotationPoint(-1.0F, 4.0F, 2.0F);
        this.Cloth2.addBox(-1.0F, -6.0F, 0.0F, 2, 12, 3, 0.0F);
        this.Cloth4 = new Cuboid(this, 16, 38);
        this.Cloth4.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Cloth4.addBox(-4.0F, 0.0F, -3.0F, 8, 3, 6, 0.0F);
        this.Body = new Cuboid(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Nose = new Cuboid(this, 0, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, -4.0F);
        this.Nose.addBox(-1.0F, -1.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 22);
        this.RightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Hat = new Cuboid(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.25F);
        this.LeftArm = new Cuboid(this, 44, 22);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftArm, -1.3089969389957472F, -0.08726646259971647F, 0.0F);
        this.Cloth1 = new Cuboid(this, 24, 0);
        this.Cloth1.setRotationPoint(0.0F, -6.0F, 4.25F);
        this.Cloth1.addBox(-4.0F, -2.0F, 0.0F, 8, 8, 0, 0.0F);
        this.setRotateAngle(Cloth1, 0.17453292519943295F, 0.0F, 0.0F);
        this.LeftArm.addChild(this.Cloth3);
        this.RightArm.addChild(this.Cloth2);
        this.Body.addChild(this.Cloth4);
        this.Head.addChild(this.Cloth1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.RightArm.render(f5);
        this.LeftLeg.render(f5);
        this.Body.render(f5);
        this.Nose.render(f5);
        this.Head.render(f5);
        this.RightLeg.render(f5);
        this.Hat.render(f5);
        this.LeftArm.render(f5);
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
