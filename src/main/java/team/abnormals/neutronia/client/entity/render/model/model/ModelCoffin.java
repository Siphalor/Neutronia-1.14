package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Coffin - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCoffin extends EntityModel {
    public Cuboid Body1;
    public Cuboid Body2;
    public Cuboid Body3;
    public Cuboid Body4;
    public Cuboid Body5;
    public Cuboid Lid1;
    public Cuboid Lid2;

    public ModelCoffin() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Lid1 = new Cuboid(this, 36, 12);
        this.Lid1.setRotationPoint(-5.0F, 11.0F, 0.0F);
        this.Lid1.addBox(-4.0F, -2.0F, -18.0F, 10, 2, 36, 0.0F);
        this.setRotateAngle(Lid1, 0.0F, 0.0F, -0.17453292519943295F);
        this.Body1 = new Cuboid(this, 0, 0);
        this.Body1.setRotationPoint(-4.5F, 17.5F, 0.0F);
        this.Body1.addBox(-3.0F, -6.5F, -16.0F, 3, 13, 32, 0.0F);
        this.Body2 = new Cuboid(this, 0, 55);
        this.Body2.setRotationPoint(4.5F, 17.5F, 0.0F);
        this.Body2.addBox(0.0F, -6.5F, -16.0F, 3, 13, 32, 0.0F);
        this.Body3 = new Cuboid(this, 0, 100);
        this.Body3.setRotationPoint(0.0F, 17.0F, -12.5F);
        this.Body3.addBox(-5.0F, -7.0F, -3.0F, 10, 14, 3, 0.0F);
        this.Lid2 = new Cuboid(this, 36, 12);
        this.Lid2.setRotationPoint(5.0F, 11.0F, 0.0F);
        this.Lid2.addBox(-6.0F, -2.0F, -18.0F, 10, 2, 36, 0.0F);
        this.setRotateAngle(Lid2, 0.0F, 0.0F, 0.17453292519943295F);
        this.Body4 = new Cuboid(this, 0, 100);
        this.Body4.setRotationPoint(0.0F, 17.0F, 12.5F);
        this.Body4.addBox(-5.0F, -7.0F, 0.0F, 10, 14, 3, 0.0F);
        this.Body5 = new Cuboid(this, 0, 100);
        this.Body5.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.Body5.addBox(-5.0F, -1.0F, -13.0F, 10, 2, 26, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Lid1.render(f5);
        this.Body1.render(f5);
        this.Body2.render(f5);
        this.Body3.render(f5);
        this.Lid2.render(f5);
        this.Body4.render(f5);
        this.Body5.render(f5);
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
