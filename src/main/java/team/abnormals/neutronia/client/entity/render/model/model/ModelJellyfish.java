package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelJellyfish - HuskyTheArtist
 * Created using Tabula 7.0.0
 */
public class ModelJellyfish extends EntityModel {
    public Cuboid bodyUpper;
    public Cuboid bodyLower;
    public Cuboid shape8;
    public Cuboid shape8_1;
    public Cuboid shape8_2;
    public Cuboid shape8_3;
    public Cuboid shape8_4;
    public Cuboid shape8_5;
    public Cuboid shape8_6;
    public Cuboid shape8_7;
    public Cuboid shape8_8;
    public Cuboid shape8_9;
    public Cuboid shape8_10;
    public Cuboid shape8_11;

    public ModelJellyfish() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape8_6 = new Cuboid(this, 0, 20);
        this.shape8_6.setRotationPoint(3.0F, 6.0F, 4.5F);
        this.shape8_6.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_6, 0.0F, 0.0F, 0.22759093446006054F);
        this.shape8_1 = new Cuboid(this, 0, 20);
        this.shape8_1.setRotationPoint(8.0F, 6.0F, 3.0F);
        this.shape8_1.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_1, -0.22759093446006054F, 0.0F, -0.22759093446006054F);
        this.shape8_7 = new Cuboid(this, 0, 20);
        this.shape8_7.setRotationPoint(3.0F, 6.0F, 6.5F);
        this.shape8_7.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_7, 0.0F, 0.0F, 0.22759093446006054F);
        this.bodyLower = new Cuboid(this, 0, 0);
        this.bodyLower.setRotationPoint(-1.0F, 3.0F, -1.0F);
        this.bodyLower.addBox(0.0F, 0.0F, 0.0F, 15, 4, 15, 0.0F);
        this.bodyUpper = new Cuboid(this, 5, 20);
        this.bodyUpper.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyUpper.addBox(0.0F, 0.0F, 0.0F, 13, 4, 13, 0.0F);
        this.shape8_11 = new Cuboid(this, 0, 20);
        this.shape8_11.setRotationPoint(8.0F, 6.0F, 4.5F);
        this.shape8_11.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_11, 0.0F, 0.0F, -0.22759093446006054F);
        this.shape8 = new Cuboid(this, 0, 20);
        this.shape8.setRotationPoint(3.0F, 6.0F, 3.0F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8, -0.22759093446006054F, 0.0F, 0.22759093446006054F);
        this.shape8_2 = new Cuboid(this, 0, 20);
        this.shape8_2.setRotationPoint(8.0F, 6.0F, 8.0F);
        this.shape8_2.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_2, 0.22759093446006054F, 0.0F, -0.22759093446006054F);
        this.shape8_9 = new Cuboid(this, 0, 20);
        this.shape8_9.setRotationPoint(6.5F, 6.0F, 8.0F);
        this.shape8_9.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_9, 0.22759093446006054F, 0.0F, 0.0F);
        this.shape8_5 = new Cuboid(this, 0, 20);
        this.shape8_5.setRotationPoint(6.5F, 6.0F, 3.0F);
        this.shape8_5.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_5, -0.22759093446006054F, 0.0F, 0.0F);
        this.shape8_10 = new Cuboid(this, 0, 20);
        this.shape8_10.setRotationPoint(8.0F, 6.0F, 6.5F);
        this.shape8_10.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_10, 0.0F, 0.0F, -0.22759093446006054F);
        this.shape8_3 = new Cuboid(this, 0, 20);
        this.shape8_3.setRotationPoint(3.0F, 6.0F, 8.0F);
        this.shape8_3.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_3, 0.22759093446006054F, 0.0F, 0.22759093446006054F);
        this.shape8_4 = new Cuboid(this, 0, 20);
        this.shape8_4.setRotationPoint(4.5F, 6.0F, 3.0F);
        this.shape8_4.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_4, -0.22759093446006054F, 0.0F, 0.0F);
        this.shape8_8 = new Cuboid(this, 0, 20);
        this.shape8_8.setRotationPoint(4.5F, 6.0F, 8.0F);
        this.shape8_8.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        this.setRotateAngle(shape8_8, 0.22759093446006054F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape8_6.render(f5);
        this.shape8_1.render(f5);
        this.shape8_7.render(f5);
        this.bodyLower.render(f5);
        this.bodyUpper.render(f5);
        this.shape8_11.render(f5);
        this.shape8.render(f5);
        this.shape8_2.render(f5);
        this.shape8_9.render(f5);
        this.shape8_5.render(f5);
        this.shape8_10.render(f5);
        this.shape8_3.render(f5);
        this.shape8_4.render(f5);
        this.shape8_8.render(f5);
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
