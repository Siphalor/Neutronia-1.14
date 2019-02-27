package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * bramblestalk - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelBrambleStalk extends EntityModel {
    public Cuboid body01;
    public Cuboid thorns01;
    public Cuboid thorns02;
    public Cuboid thorns03;
    public Cuboid thorns04;

    public ModelBrambleStalk() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.thorns02 = new Cuboid(this, 0, 9);
        this.thorns02.setRotationPoint(-2.0F, -2.0F, 8.0F);
        this.thorns02.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns02, 0.0F, 0.0F, -0.7853981633974483F);
        this.thorns04 = new Cuboid(this, 0, 9);
        this.thorns04.setRotationPoint(-2.0F, 2.0F, 8.0F);
        this.thorns04.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns04, 0.0F, 0.0F, -2.356194490192345F);
        this.body01 = new Cuboid(this, 0, 0);
        this.body01.setRotationPoint(0.0F, 22.0F, -8.0F);
        this.body01.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 16, 0.0F);
        this.thorns01 = new Cuboid(this, 0, 9);
        this.thorns01.setRotationPoint(2.0F, -2.0F, 8.0F);
        this.thorns01.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns01, 0.0F, 0.0F, 0.7853981633974483F);
        this.thorns03 = new Cuboid(this, 0, 9);
        this.thorns03.setRotationPoint(2.0F, 2.0F, 8.0F);
        this.thorns03.addBox(0.0F, -1.9F, -8.0F, 0, 2, 16, 0.0F);
        this.setRotateAngle(thorns03, 0.0F, 0.0F, 2.356194490192345F);
        this.body01.addChild(this.thorns02);
        this.body01.addChild(this.thorns04);
        this.body01.addChild(this.thorns01);
        this.body01.addChild(this.thorns03);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body01.render(f5);
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
