package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

public class ModelWhale extends EntityModel {
    public Cuboid body1;
    public Cuboid body2;
    public Cuboid tail1;
    public Cuboid tail2;
    public Cuboid tail3;
    public Cuboid fin1;
    public Cuboid fin2;

    public ModelWhale() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.tail1 = new Cuboid(this, 0, 21);
        this.tail1.mirror = true;
        this.tail1.setRotationPoint(0.0F, 20.0F, 3.0F);
        this.tail1.addBox(-2.5F, -1.0F, 8.0F, 5, 2, 6, 0.0F);
        this.fin2 = new Cuboid(this, 35, 21);
        this.fin2.mirror = true;
        this.fin2.setRotationPoint(-3.0F, 20.0F, 0.0F);
        this.fin2.addBox(-2.0F, 1.0F, 0.0F, 2, 1, 5, 0.0F);
        this.tail2 = new Cuboid(this, 18, 21);
        this.tail2.mirror = true;
        this.tail2.setRotationPoint(0.0F, 20.0F, 3.0F);
        this.tail2.addBox(-5.0F, 0.0F, 13.0F, 3, 1, 5, 0.0F);
        this.body2 = new Cuboid(this, 28, 0);
        this.body2.mirror = true;
        this.body2.setRotationPoint(0.0F, 20.0F, 3.0F);
        this.body2.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 9, 0.0F);
        this.fin1 = new Cuboid(this, 35, 21);
        this.fin1.mirror = true;
        this.fin1.setRotationPoint(3.0F, 20.0F, 0.0F);
        this.fin1.addBox(0.0F, 1.0F, 0.0F, 2, 1, 5, 0.0F);
        this.body1 = new Cuboid(this, 0, 0);
        this.body1.mirror = true;
        this.body1.setRotationPoint(0.0F, 19.0F, -4.0F);
        this.body1.addBox(-3.5F, -2.5F, -7.0F, 7, 7, 14, 0.0F);
        this.tail3 = new Cuboid(this, 18, 21);
        this.tail3.mirror = true;
        this.tail3.setRotationPoint(0.0F, 20.0F, 3.0F);
        this.tail3.addBox(2.0F, 0.0F, 13.0F, 3, 1, 5, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.tail1.render(f5);
        this.fin2.render(f5);
        this.tail2.render(f5);
        this.body2.render(f5);
        this.fin1.render(f5);
        this.body1.render(f5);
        this.tail3.render(f5);
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
