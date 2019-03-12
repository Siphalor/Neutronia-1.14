package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * Enchanter Villager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelEnchanterVillager extends EntityModel {
    public Cuboid Head;
    public Cuboid Nose;
    public Cuboid Robes;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid MiddleArm;
    public Cuboid Hat1;
    public Cuboid Hat2;
    public Cuboid Hat3;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Body;

    public ModelEnchanterVillager() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.Hat1 = new Cuboid(this, 0, 64);
        this.Hat1.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.Hat1.addBox(-5.0F, -2.0F, -5.0F, 10, 2, 10, 0.0F);
        this.Body = new Cuboid(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.MiddleArm = new Cuboid(this, 40, 38);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.RightArm = new Cuboid(this, 44, 22);
        this.RightArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(RightArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Hat3 = new Cuboid(this, 12, 76);
        this.Hat3.setRotationPoint(0.0F, -15.0F, -1.5F);
        this.Hat3.addBox(-2.5F, -5.0F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(Hat3, 0.2617993877991494F, 0.4363323129985824F, -0.17453292519943295F);
        this.RightLeg = new Cuboid(this, 0, 22);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.Nose = new Cuboid(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, -4.0F);
        this.Nose.addBox(-1.0F, -1.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Robes = new Cuboid(this, 0, 38);
        this.Robes.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Robes.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.Hat2 = new Cuboid(this, 32, 68);
        this.Hat2.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.Hat2.addBox(-4.0F, -6.0F, -4.0F, 8, 6, 8, 0.0F);
        this.setRotateAngle(Hat2, 0.18203784098300857F, 0.08726646259971647F, -0.04363323129985824F);
        this.LeftArm = new Cuboid(this, 44, 22);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(LeftArm, -0.7853981633974483F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Hat1.render(f5);
        this.Body.render(f5);
        this.MiddleArm.render(f5);
        this.RightArm.render(f5);
        this.Hat3.render(f5);
        this.RightLeg.render(f5);
        this.LeftLeg.render(f5);
        this.Head.render(f5);
        this.Nose.render(f5);
        this.Robes.render(f5);
        this.Hat2.render(f5);
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
