package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelChornet - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelChornet extends EntityModel {
    public Cuboid head;
    public Cuboid lCarapace;
    public Cuboid rCarapace;
    public Cuboid antenna;
    public Cuboid abdomen;
    public Cuboid sting;

    public ModelChornet() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.sting = new Cuboid(this, 46, 0);
        this.sting.setRotationPoint(0.0F, 2.2F, -3.3F);
        this.sting.addBox(-0.5F, -0.5F, -2.6F, 1, 1, 3, 0.0F);
        this.setRotateAngle(sting, 0.2792526803190927F, 0.0F, 0.0F);
        this.lCarapace = new Cuboid(this, 49, 0);
        this.lCarapace.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.lCarapace.addBox(0.0F, -8.0F, -8.0F, 9, 16, 16, 0.0F);
        this.antenna = new Cuboid(this, 28, 0);
        this.antenna.setRotationPoint(0.0F, -2.8F, -5.8F);
        this.antenna.addBox(-2.0F, 0.0F, -4.0F, 4, 0, 4, 0.0F);
        this.setRotateAngle(antenna, -0.7853981633974483F, 0.0F, 0.0F);
        this.head = new Cuboid(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-3.5F, -3.5F, -6.0F, 7, 7, 9, 0.0F);
        this.setRotateAngle(head, -0.13962634015954636F, 0.0F, 0.0F);
        this.rCarapace = new Cuboid(this, 12, 32);
        this.rCarapace.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.rCarapace.addBox(-9.0F, -8.0F, -8.0F, 9, 16, 16, 0.0F);
        this.abdomen = new Cuboid(this, 0, 24);
        this.abdomen.setRotationPoint(0.0F, 3.3F, 2.3F);
        this.abdomen.addBox(-2.5F, -2.5F, -3.5F, 5, 5, 7, 0.0F);
        this.setRotateAngle(abdomen, 0.7853981633974483F, 0.0F, 0.0F);
        this.abdomen.addChild(this.sting);
        this.head.addChild(this.antenna);
        this.head.addChild(this.abdomen);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.lCarapace.render(f5);
        this.head.render(f5);
        this.rCarapace.render(f5);
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
