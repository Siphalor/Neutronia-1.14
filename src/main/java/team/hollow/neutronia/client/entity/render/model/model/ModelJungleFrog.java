package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import team.hollow.neutronia.entity.EntityJungleFrog;

/**
 * Frog - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelJungleFrog<T extends EntityJungleFrog> extends EntityModel<T> {
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid RightArm;
    public Cuboid LeftArm;
    public Cuboid RightEye;
    public Cuboid LeftEye;

    public ModelJungleFrog() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Body = new Cuboid(this, 0, 9);
        this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body.addBox(-5.5F, -3.0F, 0.0F, 11, 7, 11, 0.0F);
        this.LeftEye = new Cuboid(this, 0, 0);
        this.LeftEye.setRotationPoint(2.0F, 17.5F, -3.5F);
        this.LeftEye.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.RightEye = new Cuboid(this, 0, 0);
        this.RightEye.setRotationPoint(-2.0F, 17.5F, -3.5F);
        this.RightEye.addBox(-0.5F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.RightArm = new Cuboid(this, 32, 0);
        this.RightArm.setRotationPoint(6.5F, 22.0F, 1.0F);
        this.RightArm.addBox(-1.0F, -1.0F, -5.0F, 3, 3, 6, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Head.addBox(-5.5F, -1.0F, -5.0F, 11, 4, 5, 0.0F);
        this.LeftArm = new Cuboid(this, 32, 0);
        this.LeftArm.setRotationPoint(-6.5F, 22.0F, 1.0F);
        this.LeftArm.addBox(-2.0F, -1.0F, -5.0F, 3, 3, 6, 0.0F);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
        this.LeftEye.render(f5);
        this.RightEye.render(f5);
        this.RightArm.render(f5);
        this.Head.render(f5);
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
