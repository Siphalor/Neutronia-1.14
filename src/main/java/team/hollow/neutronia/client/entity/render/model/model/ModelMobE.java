package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * Mob E - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMobE extends EntityModel {
    public Cuboid Body;
    public Cuboid Head;
    public Cuboid Sting;
    public Cuboid RightWing;
    public Cuboid LeftWing;
    public Cuboid Antenna;

    public ModelMobE() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Antenna = new Cuboid(this, -4, 4);
        this.Antenna.setRotationPoint(0.0F, -3.5F, -6.0F);
        this.Antenna.addBox(-2.0F, 0.0F, -4.0F, 4, 0, 4, 0.0F);
        this.setRotateAngle(Antenna, -0.6981317007977318F, 0.0F, 0.0F);
        this.RightWing = new Cuboid(this, 28, 8);
        this.RightWing.setRotationPoint(-5.5F, 0.0F, -1.0F);
        this.RightWing.addBox(-6.0F, -6.0F, -6.0F, 6, 12, 12, 0.0F);
        this.setRotateAngle(RightWing, -0.6981317007977318F, 0.2617993877991494F, 0.0F);
        this.Sting = new Cuboid(this, 24, 4);
        this.Sting.setRotationPoint(0.0F, 7.5F, -2.0F);
        this.Sting.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Sting, -1.3962634015954636F, 0.0F, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Head, -0.6981317007977318F, 0.0F, 0.0F);
        this.LeftWing = new Cuboid(this, 28, 32);
        this.LeftWing.setRotationPoint(5.5F, 0.0F, -1.0F);
        this.LeftWing.addBox(0.0F, -6.0F, -6.0F, 6, 12, 12, 0.0F);
        this.setRotateAngle(LeftWing, -0.6981317007977318F, -0.2617993877991494F, 0.0F);
        this.Body = new Cuboid(this, 0, 17);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-2.5F, -2.0F, -2.5F, 5, 10, 5, 0.0F);
        this.setRotateAngle(Body, 0.6981317007977318F, 0.0F, 0.0F);
        this.Head.addChild(this.Antenna);
        this.Body.addChild(this.RightWing);
        this.Body.addChild(this.Sting);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.LeftWing);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
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
