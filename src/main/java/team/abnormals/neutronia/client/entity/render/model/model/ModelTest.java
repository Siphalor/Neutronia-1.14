//Made with Blockbench

package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

public class ModelTest extends EntityModel {

    //fields
    Cuboid e0;
    Cuboid e1;
    Cuboid e2;
    Cuboid e3;
    Cuboid e4;
    Cuboid e5;
    Cuboid e6;
    Cuboid e7;
    Cuboid e8;
    Cuboid e9;
    Cuboid e10;
    Cuboid e11;
    Cuboid e12;
    Cuboid e13;
    Cuboid e14;
    Cuboid e15;
    Cuboid e16;

    public ModelTest() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.e0 = new Cuboid(this, 0, 0);
        this.e0.addBox(-4F, 12F, -2F, 8, 12, 4, 1.0F);
        this.e1 = new Cuboid(this, 0, 0);
        this.e1.addBox(-4F, 24F, -4F, 8, 8, 8, 1.0F);
        this.e2 = new Cuboid(this, 0, 0);
        this.e2.addBox(-8F, 12F, -2F, 4, 12, 4, 1.0F);
        this.e3 = new Cuboid(this, 0, 0);
        this.e3.addBox(4F, 12F, -2F, 4, 12, 4, 1.0F);
        this.e4 = new Cuboid(this, 0, 0);
        this.e4.addBox(-4F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e5 = new Cuboid(this, 0, 0);
        this.e5.addBox(0F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e6 = new Cuboid(this, 0, 0);
        this.e6.addBox(-4F, 12F, -2F, 8, 12, 4, 1.0F);
        this.e7 = new Cuboid(this, 0, 0);
        this.e7.addBox(0F, 28F, -2.5F, 5, 4, 1, 1.0F);
        this.e8 = new Cuboid(this, 0, 0);
        this.e8.addBox(1.5F, 28F, -5F, 1, 4, 5, 1.0F);
        this.e9 = new Cuboid(this, 0, 0);
        this.e9.addBox(0F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e10 = new Cuboid(this, 0, 0);
        this.e10.addBox(-3.9999998807907F, 0F, -2F, 4, 12, 4, 1.0F);
        this.e11 = new Cuboid(this, 0, 0);
        this.e11.addBox(-10F, 24F, 1.5F, 5, 4, 1, 1.0F);
        this.e12 = new Cuboid(this, 0, 0);
        this.e12.addBox(-8F, 24F, -1F, 1, 4, 5, 1.0F);
        this.e13 = new Cuboid(this, 0, 0);
        this.e13.addBox(-7.5F, 24F, -1.5F, 3, 3, 1, 1.0F);
        this.e14 = new Cuboid(this, 0, 0);
        this.e14.addBox(-6.5F, 24F, -3F, 1, 3, 3, 1.0F);
        this.e15 = new Cuboid(this, 0, 0);
        this.e15.addBox(6F, 24F, -1.5F, 3, 3, 1, 1.0F);
        this.e16 = new Cuboid(this, 0, 0);
        this.e16.addBox(7F, 24F, -3F, 1, 3, 3, 1.0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.e0.render(f5);
        this.e1.render(f5);
        this.e2.render(f5);
        this.e3.render(f5);
        this.e4.render(f5);
        this.e5.render(f5);
        this.e6.render(f5);
        this.e7.render(f5);
        this.e8.render(f5);
        this.e9.render(f5);
        this.e10.render(f5);
        this.e11.render(f5);
        this.e12.render(f5);
        this.e13.render(f5);
        this.e14.render(f5);
        this.e15.render(f5);
        this.e16.render(f5);

    }

    public void setRotateAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.rotationPointX = x;
        Cuboid.rotationPointY = y;
        Cuboid.rotationPointZ = z;
    }
}