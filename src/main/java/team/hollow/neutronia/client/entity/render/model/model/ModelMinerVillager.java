package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * Miner Villager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMinerVillager extends EntityModel {
    public Cuboid Helmet1;
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid MiddleArm;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Nose;
    public Cuboid Helmet2;

    public ModelMinerVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new Cuboid(this, 44, 22);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(LeftArm, -0.9599310885968813F, 0.0F, 0.0F);
        this.Helmet2 = new Cuboid(this, 40, 19);
        this.Helmet2.setRotationPoint(0.0F, -10.0F, -5.0F);
        this.Helmet2.addBox(-1.0F, -4.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(Helmet2, 0.2617993877991494F, 0.0F, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleArm = new Cuboid(this, 40, 38);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.9599310885968813F, 0.0F, 0.0F);
        this.Body = new Cuboid(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 22);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Helmet1 = new Cuboid(this, 32, 0);
        this.Helmet1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Helmet1.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.75F);
        this.RightArm = new Cuboid(this, 44, 22);
        this.RightArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(RightArm, -0.9599310885968813F, 0.0F, 0.0F);
        this.Nose = new Cuboid(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, -4.0F);
        this.Nose.addBox(-1.0F, -1.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftArm.render(f5);
        this.Helmet2.render(f5);
        this.LeftLeg.render(f5);
        this.MiddleArm.render(f5);
        this.Body.render(f5);
        this.RightLeg.render(f5);
        this.Helmet1.render(f5);
        this.RightArm.render(f5);
        this.Nose.render(f5);
        this.Head.render(f5);
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
