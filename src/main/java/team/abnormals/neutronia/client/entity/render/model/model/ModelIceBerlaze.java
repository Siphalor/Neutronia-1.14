package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * IceBerlaze - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelIceBerlaze extends EntityModel {
    public Cuboid Body;
    public Cuboid Head1;
    public Cuboid Neck1;
    public Cuboid Neck2;
    public Cuboid LittleHead2;
    public Cuboid Legs1;
    public Cuboid Shield1;
    public Cuboid Shield2;
    public Cuboid Shield3;
    public Cuboid Shield4;
    public Cuboid Shield5;
    public Cuboid Shield6;
    public Cuboid Shield7;
    public Cuboid Shield8;
    public Cuboid LittleHead1;
    public Cuboid Head2;
    public Cuboid Legs2;

    public ModelIceBerlaze() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Shield3 = new Cuboid(this, 56, 0);
        this.Shield3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield3.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield3, 0.0F, -1.1780972450961724F, 0.0F);
        this.Shield7 = new Cuboid(this, 56, 0);
        this.Shield7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield7.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield7, 0.0F, 1.9634954084936207F, 0.0F);
        this.Head1 = new Cuboid(this, 0, 0);
        this.Head1.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Head1.addBox(-3.0F, -2.0F, -3.5F, 6, 2, 7, 0.0F);
        this.setRotateAngle(Head1, 0.8726646259971648F, 0.0F, 0.0F);
        this.Legs2 = new Cuboid(this, 12, 24);
        this.Legs2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Legs2.addBox(-2.0F, 6.0F, -7.5F, 4, 2, 6, 0.0F);
        this.Shield1 = new Cuboid(this, 56, 0);
        this.Shield1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield1.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield1, 0.0F, 0.39269908169872414F, 0.0F);
        this.Shield4 = new Cuboid(this, 56, 0);
        this.Shield4.mirror = true;
        this.Shield4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield4.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield4, 0.0F, -1.9634954084936207F, 0.0F);
        this.LittleHead1 = new Cuboid(this, 26, 12);
        this.LittleHead1.setRotationPoint(-9.5F, -5.0F, 0.0F);
        this.LittleHead1.addBox(-4.0F, -4.0F, -2.5F, 4, 5, 5, 0.0F);
        this.Shield8 = new Cuboid(this, 56, 0);
        this.Shield8.mirror = true;
        this.Shield8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield8.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield8, 0.0F, 2.748893571891069F, 0.0F);
        this.Neck2 = new Cuboid(this, 40, 12);
        this.Neck2.mirror = true;
        this.Neck2.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Neck2.addBox(1.5F, 16.0F, -1.5F, 8, 2, 3, 0.0F);
        this.Legs1 = new Cuboid(this, 12, 13);
        this.Legs1.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Legs1.addBox(-2.0F, 0.0F, -1.5F, 4, 8, 3, 0.0F);
        this.setRotateAngle(Legs1, 0.8726646259971648F, 0.0F, 0.0F);
        this.Shield2 = new Cuboid(this, 56, 0);
        this.Shield2.mirror = true;
        this.Shield2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield2.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield2, 0.0F, -0.39269908169872414F, 0.0F);
        this.Shield6 = new Cuboid(this, 56, 0);
        this.Shield6.mirror = true;
        this.Shield6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield6.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield6, 0.0F, 1.1780972450961724F, 0.0F);
        this.Body = new Cuboid(this, 0, 11);
        this.Body.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Body.addBox(-1.5F, 0.0F, -1.5F, 3, 18, 3, 0.0F);
        this.Neck1 = new Cuboid(this, 40, 12);
        this.Neck1.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Neck1.addBox(-9.5F, 0.0F, -1.5F, 8, 2, 3, 0.0F);
        this.Head2 = new Cuboid(this, 28, 0);
        this.Head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head2.addBox(-3.0F, -8.0F, 1.5F, 6, 6, 2, 0.0F);
        this.Shield5 = new Cuboid(this, 56, 0);
        this.Shield5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shield5.addBox(-1.5F, -1.0F, -12.0F, 3, 6, 1, 0.0F);
        this.setRotateAngle(Shield5, 0.0F, -2.748893571891069F, 0.0F);
        this.LittleHead2 = new Cuboid(this, 32, 22);
        this.LittleHead2.setRotationPoint(9.5F, 11.0F, 0.0F);
        this.LittleHead2.addBox(0.0F, -4.0F, -2.5F, 4, 5, 5, 0.0F);
        this.Legs1.addChild(this.Legs2);
        this.Head1.addChild(this.Head2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Shield3.render(f5);
        this.Shield7.render(f5);
        this.Head1.render(f5);
        this.Shield1.render(f5);
        this.Shield4.render(f5);
        this.LittleHead1.render(f5);
        this.Shield8.render(f5);
        this.Neck2.render(f5);
        this.Legs1.render(f5);
        this.Shield2.render(f5);
        this.Shield6.render(f5);
        this.Body.render(f5);
        this.Neck1.render(f5);
        this.Shield5.render(f5);
        this.LittleHead2.render(f5);
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
