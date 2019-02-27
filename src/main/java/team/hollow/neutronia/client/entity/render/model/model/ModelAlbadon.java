//Made with Blockbench

package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

public class ModelAlbadon extends EntityModel {

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

    public ModelAlbadon() {
        this.textureWidth = 16;
        this.textureHeight = 16;

        this.e0 = new Cuboid(this, 0, 0);
        this.e0.addBox(4F, 5F, -7F, 8, 5, 8, 1.0F);
        this.e1 = new Cuboid(this, 0, 0);
        this.e1.addBox(4F, 4F, -6F, 8, 1, 7, 1.0F);
        this.e1.setRotationPoint(4F, 4F, -6F);
        this.setRotateAngle(e1, 0F, 0.0F, 0.0F);
        this.e2 = new Cuboid(this, 0, 0);
        this.e2.addBox(2F, 4F, 1F, 12, 8, 11, 1.0F);
        this.e3 = new Cuboid(this, 0, 0);
        this.e3.addBox(4F, 5F, 12F, 8, 5, 4, 1.0F);
        this.e4 = new Cuboid(this, 0, 0);
        this.e4.addBox(6F, 5F, 16F, 4, 3, 5, 1.0F);
        this.e5 = new Cuboid(this, 0, 0);
        this.e5.addBox(3F, 6F, 20F, 10, 1, 6, 1.0F);
        this.e6 = new Cuboid(this, 0, 0);
        this.e6.addBox(-6F, 7F, 3F, 8, 1, 5, 1.0F);
        this.e6.setRotationPoint(-6F, 7F, 3F);
        this.setRotateAngle(e6, 0.0F, 0.0F, 0F);
        this.e7 = new Cuboid(this, 0, 0);
        this.e7.addBox(14F, 7F, 3F, 8, 1, 5, 1.0F);
        this.e8 = new Cuboid(this, 0, 0);
        this.e8.addBox(7.5F, 10.75F, 5F, 1, 6, 4, 1.0F);
        this.e8.setRotationPoint(8F, 13.75F, 7F);
        this.setRotateAngle(e8, 22.5F, 0.0F, 0.0F);
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
    }

    public void setRotateAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.rotationPointX = x;
        Cuboid.rotationPointY = y;
        Cuboid.rotationPointZ = z;
    }

}