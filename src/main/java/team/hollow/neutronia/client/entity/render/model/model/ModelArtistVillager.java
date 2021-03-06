package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelArtistVillager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelArtistVillager extends EntityModel {
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid MiddleArm;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid HatLayer;
    public Cuboid Apron;
    public Cuboid Hat;
    public Cuboid Nose;
    public Cuboid HatTop;
    public Cuboid RightArm;
    public Cuboid LeftArm;

    public ModelArtistVillager() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.RightArm = new Cuboid(this, 24, 50);
        this.RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.HatLayer = new Cuboid(this, 32, 0);
        this.HatLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HatLayer.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.25F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.HatTop = new Cuboid(this, 0, 18);
        this.HatTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HatTop.addBox(0.0F, -3.0F, 0.0F, 1, 1, 1, 0.0F);
        this.LeftLeg = new Cuboid(this, 40, 18);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleArm = new Cuboid(this, 0, 50);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Apron = new Cuboid(this, 0, 58);
        this.Apron.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Apron.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.Body = new Cuboid(this, 0, 32);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.LeftArm = new Cuboid(this, 24, 50);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Hat = new Cuboid(this, 0, 18);
        this.Hat.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.Hat.addBox(-5.0F, -2.0F, -5.0F, 10, 4, 10, 0.0F);
        this.setRotateAngle(Hat, -0.17453292519943295F, 0.08726646259971647F, 0.0F);
        this.RightLeg = new Cuboid(this, 40, 18);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Nose = new Cuboid(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.MiddleArm.addChild(this.RightArm);
        this.Hat.addChild(this.HatTop);
        this.MiddleArm.addChild(this.LeftArm);
        this.Head.addChild(this.Hat);
        this.Head.addChild(this.Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.HatLayer.render(f5);
        this.Head.render(f5);
        this.LeftLeg.render(f5);
        this.MiddleArm.render(f5);
        this.Apron.render(f5);
        this.Body.render(f5);
        this.RightLeg.render(f5);
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
