package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelIceleech - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelIceLeech extends EntityModel {
    public Cuboid shape1;
    public Cuboid shape1_1;
    public Cuboid shape1_2;
    public Cuboid shape1_3;
    public Cuboid shape1_4;
    public Cuboid shape1_5;

    public ModelIceLeech() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1_2 = new Cuboid(this, 38, 8);
        this.shape1_2.setRotationPoint(-1.5F, 21.0F, 12.0F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        this.shape1_5 = new Cuboid(this, -8, 18);
        this.shape1_5.setRotationPoint(-4.0F, 23.0F, 15.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 8, 0, 10, 0.0F);
        this.shape1 = new Cuboid(this, 0, 0);
        this.shape1.setRotationPoint(-2.5F, 19.0F, -3.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
        this.shape1_3 = new Cuboid(this, 20, 0);
        this.shape1_3.setRotationPoint(-2.0F, 20.0F, -7.8F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
        this.shape1_4 = new Cuboid(this, 44, 0);
        this.shape1_4.setRotationPoint(-1.0F, 22.0F, 16.0F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.shape1_1 = new Cuboid(this, 25, 10);
        this.shape1_1.setRotationPoint(-2.0F, 20.0F, 7.0F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1_2.render(f5);
        this.shape1_5.render(f5);
        this.shape1.render(f5);
        this.shape1_3.render(f5);
        this.shape1_4.render(f5);
        this.shape1_1.render(f5);
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
