package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Cortex - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCortex extends EntityModel {
    public Cuboid Shell;
    public Cuboid Eye;
    public Cuboid Brain;
    public Cuboid ClosedEye;
    public Cuboid Arm1;
    public Cuboid Arm2;
    public Cuboid Arm3;
    public Cuboid Arm4;
    public Cuboid Arm5;
    public Cuboid Arm6;
    public Cuboid Arm7;
    public Cuboid Arm8;
    public Cuboid Arm9;
    public Cuboid Arm10;

    public ModelCortex() {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.Arm6 = new Cuboid(this, 0, 88);
        this.Arm6.setRotationPoint(-6.0F, 11.0F, 6.0F);
        this.Arm6.addBox(0.0F, 0.0F, -12.0F, 1, 12, 12, 0.0F);
        this.setRotateAngle(Arm6, 0.17453292519943295F, 2.356194490192345F, 0.0F);
        this.Arm8 = new Cuboid(this, 26, 77);
        this.Arm8.setRotationPoint(-1.0F, 11.0F, 1.0F);
        this.Arm8.addBox(0.0F, 0.0F, -6.0F, 1, 16, 6, 0.0F);
        this.setRotateAngle(Arm8, 0.0F, 2.356194490192345F, 0.0F);
        this.Arm4 = new Cuboid(this, 26, 64);
        this.Arm4.setRotationPoint(4.0F, 11.0F, 0.0F);
        this.Arm4.addBox(-12.0F, 0.0F, 0.0F, 12, 12, 1, 0.0F);
        this.setRotateAngle(Arm4, 0.0F, 3.141592653589793F, 0.17453292519943295F);
        this.Arm9 = new Cuboid(this, 26, 77);
        this.Arm9.setRotationPoint(1.0F, 11.0F, -1.0F);
        this.Arm9.addBox(0.0F, 0.0F, -6.0F, 1, 16, 6, 0.0F);
        this.setRotateAngle(Arm9, 0.0F, -0.7853981633974483F, -0.0F);
        this.Arm5 = new Cuboid(this, 0, 88);
        this.Arm5.setRotationPoint(6.0F, 11.0F, -6.0F);
        this.Arm5.addBox(0.0F, 0.0F, -12.0F, 1, 12, 12, 0.0F);
        this.setRotateAngle(Arm5, 0.17453292519943295F, -0.7853981633974483F, 0.0F);
        this.Arm10 = new Cuboid(this, 26, 77);
        this.Arm10.setRotationPoint(1.0F, 11.0F, 1.0F);
        this.Arm10.addBox(0.0F, 0.0F, -6.0F, 1, 16, 6, 0.0F);
        this.setRotateAngle(Arm10, 0.0F, -2.356194490192345F, 0.0F);
        this.Arm1 = new Cuboid(this, 0, 64);
        this.Arm1.setRotationPoint(-6.0F, 11.0F, -6.0F);
        this.Arm1.addBox(0.0F, 0.0F, -12.0F, 1, 12, 12, 0.0F);
        this.setRotateAngle(Arm1, 0.17453292519943295F, 0.7853981633974483F, 0.0F);
        this.ClosedEye = new Cuboid(this, 0, 5);
        this.ClosedEye.setRotationPoint(0.0F, 5.0F, -7.5F);
        this.ClosedEye.addBox(-2.5F, -0.5F, -1.0F, 5, 1, 1, 0.0F);
        this.Arm7 = new Cuboid(this, 26, 77);
        this.Arm7.setRotationPoint(-1.0F, 11.0F, -1.0F);
        this.Arm7.addBox(0.0F, 0.0F, -6.0F, 1, 16, 6, 0.0F);
        this.setRotateAngle(Arm7, 0.0F, 0.7853981633974483F, 0.0F);
        this.Shell = new Cuboid(this, 0, 32);
        this.Shell.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shell.addBox(-8.0F, -4.0F, -8.0F, 16, 16, 16, 0.25F);
        this.Eye = new Cuboid(this, 0, 0);
        this.Eye.setRotationPoint(0.0F, 0.0F, -8.5F);
        this.Eye.addBox(-1.0F, 3.5F, 0.0F, 2, 4, 1, 0.0F);
        this.Arm2 = new Cuboid(this, 0, 64);
        this.Arm2.setRotationPoint(6.0F, 11.0F, 6.0F);
        this.Arm2.addBox(0.0F, 0.0F, -12.0F, 1, 12, 12, 0.0F);
        this.setRotateAngle(Arm2, 0.17453292519943295F, -2.356194490192345F, 0.0F);
        this.Arm3 = new Cuboid(this, 26, 64);
        this.Arm3.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.Arm3.addBox(-12.0F, 0.0F, 0.0F, 12, 12, 1, 0.0F);
        this.setRotateAngle(Arm3, 0.0F, 0.0F, -0.17453292519943295F);
        this.Brain = new Cuboid(this, 0, 0);
        this.Brain.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Brain.addBox(-8.0F, -4.0F, -8.0F, 16, 16, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Arm6.render(f5);
        this.Arm8.render(f5);
        this.Arm4.render(f5);
        this.Arm9.render(f5);
        this.Arm5.render(f5);
        this.Arm10.render(f5);
        this.Arm1.render(f5);
        this.ClosedEye.render(f5);
        this.Arm7.render(f5);
        this.Shell.render(f5);
        this.Eye.render(f5);
        this.Arm2.render(f5);
        this.Arm3.render(f5);
        this.Brain.render(f5);
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
