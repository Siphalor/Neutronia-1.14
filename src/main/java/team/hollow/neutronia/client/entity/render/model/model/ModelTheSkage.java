package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * The Skage - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelTheSkage extends EntityModel {
    public Cuboid Hat;
    public Cuboid Body;
    public Cuboid Head;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid Hip;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;

    public ModelTheSkage() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.LeftArm = new Cuboid(this, 0, 16);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(7.5F, 2.5F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LeftLeg = new Cuboid(this, 10, 16);
        this.LeftLeg.setRotationPoint(2.0F, 2.0F, -2.0F);
        this.LeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.RightArm = new Cuboid(this, 0, 16);
        this.RightArm.setRotationPoint(-7.5F, 2.5F, 0.0F);
        this.RightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
        this.Body = new Cuboid(this, 0, 32);
        this.Body.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Body.addBox(-6.5F, 0.0F, -6.5F, 13, 13, 13, 0.0F);
        this.RightLeg = new Cuboid(this, 10, 16);
        this.RightLeg.setRotationPoint(-2.0F, 2.0F, -2.0F);
        this.RightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.Hat = new Cuboid(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.Hip = new Cuboid(this, 20, 18);
        this.Hip.setRotationPoint(0.0F, 13.0F, 3.5F);
        this.Hip.addBox(-4.0F, 0.0F, -4.0F, 8, 4, 4, 0.0F);
        this.Body.addChild(this.LeftArm);
        this.Body.addChild(this.Head);
        this.Hip.addChild(this.LeftLeg);
        this.Body.addChild(this.RightArm);
        this.Hip.addChild(this.RightLeg);
        this.Body.addChild(this.Hip);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
        this.Hat.render(f5);
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
