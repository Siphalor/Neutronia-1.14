package team.hollow.neutronia.client.entity.render.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelWitch - Undefined
 * Created using Tabula 7.0.1
 */
public class WitchModel extends EntityModel<WitchEntity> {
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid Cloak;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid MiddleClosedArm;
    public Cuboid RightOpenArm;
    public Cuboid LeftOpenArm;
    public Cuboid Nose;
    public Cuboid Hat1;
    public Cuboid Hat2;
    public Cuboid Hat3;
    public Cuboid RightClosedArm;
    public Cuboid LeftClosedArm;

    public WitchModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RightLeg = new Cuboid(this, 0, 16);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleClosedArm = new Cuboid(this, 38, 12);
        this.MiddleClosedArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleClosedArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleClosedArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Nose = new Cuboid(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Body = new Cuboid(this, 16, 16);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.LeftOpenArm = new Cuboid(this, 44, 20);
        this.LeftOpenArm.mirror = true;
        this.LeftOpenArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftOpenArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightOpenArm = new Cuboid(this, 44, 20);
        this.RightOpenArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightOpenArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftClosedArm = new Cuboid(this, 32, 0);
        this.LeftClosedArm.mirror = true;
        this.LeftClosedArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftClosedArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 16);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Hat1 = new Cuboid(this, 24, 52);
        this.Hat1.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.Hat1.addBox(-5.0F, -1.0F, -5.0F, 10, 2, 10);
        Hat1.pitch = -0.05F;
        this.Hat2 = new Cuboid(this, 32, 39);
        this.Hat2.setRotationPoint(0.0F, -2.5F, 0.0F);
        this.Hat2.addBox(-4.0F, -2.50F, -4.0F, 8, 5, 8);
        Hat2.pitch = -0.2F;
        this.Hat3 = new Cuboid(this, 48, 3);
        this.Hat3.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Hat3.addBox(-2.0F, -2.5F, -1.0F, 4, 5, 4);
        Hat3.pitch = -0.2F;
        this.Cloak = new Cuboid(this, 0, 34);
        this.Cloak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.RightClosedArm = new Cuboid(this, 32, 0);
        this.RightClosedArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightClosedArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.MiddleClosedArm.addChild(this.LeftClosedArm);
        this.Hat2.addChild(this.Hat3);
        this.Hat1.addChild(this.Hat2);
        this.Head.addChild(this.Hat1);
        this.Head.addChild(this.Nose);
        this.MiddleClosedArm.addChild(this.RightClosedArm);
    }

    @Override
    public void render(WitchEntity entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
//        this.setAngles(entity_1, float_1, float_2, float_3, float_4, float_5, float_6);
        this.RightLeg.render(float_6);
        this.MiddleClosedArm.render(float_6);
        this.Body.render(float_6);
        this.LeftOpenArm.render(float_6);
        this.RightOpenArm.render(float_6);
        this.Head.render(float_6);
        this.LeftLeg.render(float_6);
        this.Cloak.render(float_6);
    }

    public void setAngles(WitchEntity entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
        boolean boolean_1 = false;

        this.Head.yaw = float_4 * 0.017453292F;
        this.Head.pitch = float_5 * 0.017453292F;
        if (boolean_1) {
            this.Head.roll = 0.3F * MathHelper.sin(0.45F * float_3);
            this.Head.pitch = 0.4F;
        } else {
            this.Head.roll = 0.0F;
        }

        this.LeftClosedArm.rotationPointY = 3.0F;
        this.LeftClosedArm.rotationPointZ = -1.0F;
        this.LeftClosedArm.pitch = -0.75F;
        this.RightClosedArm.rotationPointY = 3.0F;
        this.RightClosedArm.rotationPointZ = -1.0F;
        this.RightClosedArm.pitch = -0.75F;
        this.LeftLeg.pitch = MathHelper.cos(float_1 * 0.6662F) * 1.4F * float_2 * 0.5F;
        this.RightLeg.pitch = MathHelper.cos(float_1 * 0.6662F + 3.1415927F) * 1.4F * float_2 * 0.5F;
        this.LeftLeg.yaw = 0.0F;
        this.RightLeg.yaw = 0.0F;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
    }
}
