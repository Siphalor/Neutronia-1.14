package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelCreeder - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCreeder extends EntityModel {
    public Cuboid Body;
    public Cuboid RightLeg1;
    public Cuboid RightLeg2;
    public Cuboid RightLeg3;
    public Cuboid LeftLeg1;
    public Cuboid LeftLeg2;
    public Cuboid LeftLeg3;
    public Cuboid Head;
    public Cuboid RightBottomLeg1;
    public Cuboid RightBottomLeg2;
    public Cuboid RightBottomLeg3;
    public Cuboid LeftBottomLeg1;
    public Cuboid LeftBottomLeg2;
    public Cuboid LeftBottomLeg3;

    public ModelCreeder() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftLeg3 = new Cuboid(this, 24, 19);
        this.LeftLeg3.setRotationPoint(4.0F, 13.5F, 0.0F);
        this.LeftLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(LeftLeg3, -1.3962634015954636F, -2.356194490192345F, 0.0F);
        this.LeftBottomLeg2 = new Cuboid(this, 48, 19);
        this.LeftBottomLeg2.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.LeftBottomLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(LeftBottomLeg2, 0.6108652381980153F, 0.0F, 0.0F);
        this.LeftBottomLeg1 = new Cuboid(this, 32, 19);
        this.LeftBottomLeg1.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.LeftBottomLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(LeftBottomLeg1, 0.6108652381980153F, 0.0F, 0.0F);
        this.RightBottomLeg1 = new Cuboid(this, 32, 19);
        this.RightBottomLeg1.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.RightBottomLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(RightBottomLeg1, 0.6108652381980153F, 0.0F, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -12.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Head, -0.17453292519943295F, 0.0F, 0.0F);
        this.RightBottomLeg3 = new Cuboid(this, 32, 19);
        this.RightBottomLeg3.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.RightBottomLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(RightBottomLeg3, 0.6108652381980153F, 0.0F, 0.0F);
        this.RightLeg1 = new Cuboid(this, 24, 19);
        this.RightLeg1.setRotationPoint(-4.0F, 13.5F, 0.0F);
        this.RightLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, -0.1F);
        this.setRotateAngle(RightLeg1, -1.3962634015954636F, 0.7853981633974483F, 0.0F);
        this.RightLeg2 = new Cuboid(this, 40, 19);
        this.RightLeg2.setRotationPoint(-4.0F, 13.5F, 0.0F);
        this.RightLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(RightLeg2, -1.3962634015954636F, 1.5707963267948966F, 0.0F);
        this.LeftLeg1 = new Cuboid(this, 24, 19);
        this.LeftLeg1.setRotationPoint(4.0F, 13.5F, 0.0F);
        this.LeftLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(LeftLeg1, -1.3962634015954636F, -0.7853981633974483F, 0.0F);
        this.RightBottomLeg2 = new Cuboid(this, 48, 19);
        this.RightBottomLeg2.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.RightBottomLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(RightBottomLeg2, 0.6108652381980153F, 0.0F, 0.0F);
        this.LeftLeg2 = new Cuboid(this, 40, 19);
        this.LeftLeg2.setRotationPoint(4.0F, 13.5F, 0.0F);
        this.LeftLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(LeftLeg2, -1.3962634015954636F, -1.5707963267948966F, 0.0F);
        this.Body = new Cuboid(this, 0, 16);
        this.Body.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.Body.addBox(-4.0F, -12.0F, -2.0F, 8, 12, 4, 0.0F);
        this.setRotateAngle(Body, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightLeg3 = new Cuboid(this, 24, 19);
        this.RightLeg3.setRotationPoint(-4.0F, 13.5F, 0.0F);
        this.RightLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(RightLeg3, -1.3962634015954636F, 2.356194490192345F, 0.0F);
        this.LeftBottomLeg3 = new Cuboid(this, 32, 19);
        this.LeftBottomLeg3.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.LeftBottomLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(LeftBottomLeg3, 0.6108652381980153F, 0.0F, 0.0F);
        this.LeftLeg2.addChild(this.LeftBottomLeg2);
        this.LeftLeg1.addChild(this.LeftBottomLeg1);
        this.RightLeg1.addChild(this.RightBottomLeg1);
        this.Body.addChild(this.Head);
        this.RightLeg3.addChild(this.RightBottomLeg3);
        this.RightLeg2.addChild(this.RightBottomLeg2);
        this.LeftLeg3.addChild(this.LeftBottomLeg3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftLeg3.render(f5);
        this.RightLeg1.render(f5);
        this.RightLeg2.render(f5);
        this.LeftLeg1.render(f5);
        this.LeftLeg2.render(f5);
        this.Body.render(f5);
        this.RightLeg3.render(f5);
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
