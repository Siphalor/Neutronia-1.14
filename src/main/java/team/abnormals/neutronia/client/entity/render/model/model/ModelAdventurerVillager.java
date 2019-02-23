package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Adventurer Villager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelAdventurerVillager extends EntityModel {
    public Cuboid Head;
    public Cuboid BackPack1;
    public Cuboid BackPack2;
    public Cuboid BackPack3;
    public Cuboid Body;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid MiddleArm;
    public Cuboid Nose;
    public Cuboid RightArm;
    public Cuboid LeftArm;

    public ModelAdventurerVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new Cuboid(this, 44, 22);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.LeftArm.addBox(4.0F, -5.0F, -1.0F, 4, 8, 4, 0.0F);
        this.BackPack1 = new Cuboid(this, 0, 38);
        this.BackPack1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackPack1.addBox(-4.0F, 0.0F, -3.0F, 8, 16, 6, 0.5F);
        this.RightArm = new Cuboid(this, 44, 22);
        this.RightArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.RightArm.addBox(-8.0F, -5.0F, -1.0F, 4, 8, 4, 0.0F);
        this.BackPack2 = new Cuboid(this, 40, 0);
        this.BackPack2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackPack2.addBox(-4.0F, 0.0F, 3.0F, 8, 8, 4, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 22);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.BackPack3 = new Cuboid(this, 38, 0);
        this.BackPack3.setRotationPoint(0.0F, 2.0F, 7.0F);
        this.BackPack3.addBox(-1.0F, -0.5F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(BackPack3, 0.17453292519943295F, 0.0F, 0.0F);
        this.Nose = new Cuboid(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.Nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.MiddleArm = new Cuboid(this, 40, 38);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Body = new Cuboid(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleArm.addChild(this.LeftArm);
        this.MiddleArm.addChild(this.RightArm);
        this.Head.addChild(this.Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BackPack1.render(f5);
        this.BackPack2.render(f5);
        this.RightLeg.render(f5);
        this.BackPack3.render(f5);
        this.MiddleArm.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
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
