package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Squirrel - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelSquirrel extends EntityModel {
    public Cuboid shape1;
    public Cuboid shape1_1;
    public Cuboid shape1_2;
    public Cuboid shape1_3;
    public Cuboid shape1_4;
    public Cuboid shape1_5;
    public Cuboid shape1_6;
    public Cuboid shape1_7;
    public Cuboid shape1_8;
    public Cuboid shape1_9;
    public Cuboid shape1_10;

    public ModelSquirrel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1_1 = new Cuboid(this, 0, 10);
        this.shape1_1.setRotationPoint(-1.9F, 22.0F, 0.1F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape1_4 = new Cuboid(this, 0, 7);
        this.shape1_4.setRotationPoint(-0.1F, 23.0F, 2.9F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape1_2 = new Cuboid(this, 0, 10);
        this.shape1_2.setRotationPoint(-0.1F, 22.0F, 0.1F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.shape1_10 = new Cuboid(this, 21, 0);
        this.shape1_10.setRotationPoint(-1.5F, 20.0F, 5.0F);
        this.shape1_10.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(shape1_10, 1.6390387005478748F, 0.0F, 0.0F);
        this.shape1_8 = new Cuboid(this, 11, 0);
        this.shape1_8.setRotationPoint(0.4F, 19.5F, -0.5F);
        this.shape1_8.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
        this.shape1_7 = new Cuboid(this, 11, 0);
        this.shape1_7.setRotationPoint(-1.4F, 19.5F, -0.5F);
        this.shape1_7.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
        this.shape1_3 = new Cuboid(this, 0, 7);
        this.shape1_3.setRotationPoint(-1.9F, 23.0F, 2.9F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.shape1_9 = new Cuboid(this, 11, 0);
        this.shape1_9.setRotationPoint(-1.5F, 22.0F, 4.0F);
        this.shape1_9.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(shape1_9, 1.1383037381507017F, 0.0F, 0.0F);
        this.shape1_5 = new Cuboid(this, 31, 0);
        this.shape1_5.setRotationPoint(-1.5F, 20.0F, -1.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.shape1 = new Cuboid(this, 0, 0);
        this.shape1.setRotationPoint(-2.0F, 21.0F, 0.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 5, 0.0F);
        this.setRotateAngle(shape1, -0.091106186954104F, 0.0F, 0.0F);
        this.shape1_6 = new Cuboid(this, 18, 0);
        this.shape1_6.setRotationPoint(-1.0F, 21.3F, -0.9F);
        this.shape1_6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 0, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1_1.render(f5);
        this.shape1_4.render(f5);
        this.shape1_2.render(f5);
        this.shape1_10.render(f5);
        this.shape1_8.render(f5);
        this.shape1_7.render(f5);
        this.shape1_3.render(f5);
        this.shape1_9.render(f5);
        this.shape1_5.render(f5);
        this.shape1.render(f5);
        this.shape1_6.render(f5);
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
