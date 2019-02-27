package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelOlDiggy - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelOlDiggy extends EntityModel {
    public Cuboid Head;
    public Cuboid HeadLayer;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid Body;
    public Cuboid BodyLayer;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid Hat1;
    public Cuboid Hat2;
    public Cuboid Hat3;

    public ModelOlDiggy() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Hat3 = new Cuboid(this, 30, 57);
        this.Hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat3.addBox(-2.0F, -11.0F, -5.0F, 4, 4, 2, 0.0F);
        this.BodyLayer = new Cuboid(this, 16, 34);
        this.BodyLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BodyLayer.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.5F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LeftArm = new Cuboid(this, 40, 34);
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightArm = new Cuboid(this, 40, 18);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Body = new Cuboid(this, 16, 18);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.Hat1 = new Cuboid(this, 0, 53);
        this.Hat1.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Hat1.addBox(-5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F);
        this.Hat2 = new Cuboid(this, 0, 54);
        this.Hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat2.addBox(-1.0F, -10.5F, -4.75F, 2, 6, 3, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 32);
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 16);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.HeadLayer = new Cuboid(this, 32, 0);
        this.HeadLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadLayer.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8, 0.25F);
        this.Head.addChild(this.Hat3);
        this.Head.addChild(this.Hat1);
        this.Head.addChild(this.Hat2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BodyLayer.render(f5);
        this.Head.render(f5);
        this.LeftArm.render(f5);
        this.RightArm.render(f5);
        this.Body.render(f5);
        this.LeftLeg.render(f5);
        this.RightLeg.render(f5);
        this.HeadLayer.render(f5);
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
