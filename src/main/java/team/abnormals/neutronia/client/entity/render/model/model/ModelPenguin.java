package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelPenguin - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelPenguin extends EntityModel {
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid Beak;
    public Cuboid Hair;
    public Cuboid RightFoot;
    public Cuboid LeftFoot;
    public Cuboid RightFlipper;
    public Cuboid LeftFlipper;

    public ModelPenguin() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Body = new Cuboid(this, 0, 15);
        this.Body.setRotationPoint(0.0F, 12.0F, 1.0F);
        this.Body.addBox(-3.5F, 0.0F, -3.0F, 7, 11, 6, 0.0F);
        this.setRotateAngle(Body, 0.17453292519943295F, 0.0F, 0.0F);
        this.Beak = new Cuboid(this, 18, 0);
        this.Beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Beak.addBox(-1.5F, -0.5F, -5.5F, 3, 1, 2, 0.0F);
        this.Hair = new Cuboid(this, 0, 0);
        this.Hair.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hair.addBox(-0.5F, -4.0F, -0.75F, 1, 1, 1, 0.0F);
        this.setRotateAngle(Hair, -0.6108652381980153F, 0.0F, 0.0F);
        this.LeftFoot = new Cuboid(this, 26, 26);
        this.LeftFoot.setRotationPoint(2.5F, 10.0F, -1.0F);
        this.LeftFoot.addBox(-2.0F, 0.0F, -3.5F, 4, 2, 4, 0.0F);
        this.setRotateAngle(LeftFoot, -0.17453292519943295F, 0.0F, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 12.0F, 0.5F);
        this.Head.addBox(-3.0F, -3.0F, -4.0F, 6, 4, 6, 0.0F);
        this.RightFlipper = new Cuboid(this, 26, 13);
        this.RightFlipper.setRotationPoint(-3.0F, 0.9F, 0.0F);
        this.RightFlipper.addBox(-1.0F, 0.0F, -2.5F, 1, 8, 5, 0.0F);
        this.LeftFlipper = new Cuboid(this, 26, 13);
        this.LeftFlipper.mirror = true;
        this.LeftFlipper.setRotationPoint(3.0F, 0.9F, 0.0F);
        this.LeftFlipper.addBox(0.0F, 0.0F, -2.5F, 1, 8, 5, 0.0F);
        this.setRotateAngle(LeftFlipper, -0.03490658503988659F, 0.0F, 0.0F);
        this.RightFoot = new Cuboid(this, 26, 26);
        this.RightFoot.setRotationPoint(-2.5F, 10.0F, -1.0F);
        this.RightFoot.addBox(-2.0F, 0.0F, -3.5F, 4, 2, 4, 0.0F);
        this.setRotateAngle(RightFoot, -0.17453292519943295F, 0.0F, 0.0F);
        this.Head.addChild(this.Beak);
        this.Head.addChild(this.Hair);
        this.Body.addChild(this.LeftFoot);
        this.Body.addChild(this.RightFlipper);
        this.Body.addChild(this.LeftFlipper);
        this.Body.addChild(this.RightFoot);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
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
