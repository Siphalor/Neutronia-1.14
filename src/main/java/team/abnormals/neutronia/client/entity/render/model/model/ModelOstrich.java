package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelOstrich - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelOstrich extends EntityModel {
    public Cuboid BodyNeck;
    public Cuboid Body;
    public Cuboid Tail;
    public Cuboid RightWing;
    public Cuboid LeftWing;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Neck1;
    public Cuboid Neck2;
    public Cuboid Head;
    public Cuboid Beak;

    public ModelOstrich() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body = new Cuboid(this, 20, 40);
        this.Body.setRotationPoint(0.0F, 11.0F, 11.0F);
        this.Body.addBox(-4.0F, -10.0F, -7.0F, 8, 10, 14, 0.0F);
        this.Tail = new Cuboid(this, 0, 53);
        this.Tail.setRotationPoint(0.0F, 6.0F, 17.5F);
        this.Tail.addBox(-2.5F, -1.0F, -1.0F, 5, 8, 3, 0.0F);
        this.setRotateAngle(Tail, 0.08726646259971647F, 0.0F, 0.0F);
        this.Beak = new Cuboid(this, 26, 33);
        this.Beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beak.addBox(-2.5F, -9.0F, -5.0F, 5, 2, 5, 0.0F);
        this.RightLeg = new Cuboid(this, 48, 9);
        this.RightLeg.setRotationPoint(-2.5F, 10.0F, 11.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -4.0F, 4, 14, 4, 0.0F);
        this.BodyNeck = new Cuboid(this, 0, 0);
        this.BodyNeck.setRotationPoint(0.0F, 9.5F, 4.0F);
        this.BodyNeck.addBox(-3.5F, -6.0F, -3.5F, 7, 7, 4, 0.0F);
        this.Neck1 = new Cuboid(this, 0, 11);
        this.Neck1.setRotationPoint(0.0F, -3.0F, -3.5F);
        this.Neck1.addBox(-2.0F, -2.0F, -3.5F, 4, 4, 4, 0.0F);
        this.setRotateAngle(Neck1, -0.03490658503988659F, 0.0F, 0.0F);
        this.RightWing = new Cuboid(this, 46, 27);
        this.RightWing.mirror = true;
        this.RightWing.setRotationPoint(3.5F, 5.9F, 11.5F);
        this.RightWing.addBox(0.0F, 0.0F, -4.0F, 1, 5, 8, 0.0F);
        this.LeftLeg = new Cuboid(this, 48, 9);
        this.LeftLeg.setRotationPoint(2.5F, 10.0F, 11.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -4.0F, 4, 14, 4, 0.0F);
        this.Neck2 = new Cuboid(this, 0, 19);
        this.Neck2.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.Neck2.addBox(-2.0F, -6.0F, -3.0F, 4, 8, 4, 0.0F);
        this.Head = new Cuboid(this, 0, 31);
        this.Head.setRotationPoint(-0.01F, -5.0F, -3.5F);
        this.Head.addBox(-2.0F, -11.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftWing = new Cuboid(this, 46, 27);
        this.LeftWing.setRotationPoint(-3.5F, 5.9F, 11.5F);
        this.LeftWing.addBox(-1.0F, 0.0F, -4.0F, 1, 5, 8, 0.0F);
        this.Head.addChild(this.Beak);
        this.BodyNeck.addChild(this.Neck1);
        this.Neck1.addChild(this.Neck2);
        this.Neck1.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
        this.Tail.render(f5);
        this.RightLeg.render(f5);
        this.BodyNeck.render(f5);
        this.RightWing.render(f5);
        this.LeftLeg.render(f5);
        this.LeftWing.render(f5);
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
