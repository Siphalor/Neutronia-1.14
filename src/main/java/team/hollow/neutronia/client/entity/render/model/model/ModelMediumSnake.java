package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * Med Snake - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelMediumSnake extends EntityModel {
    public Cuboid Head;
    public Cuboid Tongue;
    public Cuboid Body1;
    public Cuboid Body2;
    public Cuboid Body3;
    public Cuboid Body4;
    public Cuboid Body5;
    public Cuboid Body6;

    public ModelMediumSnake() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body2 = new Cuboid(this, 22, 7);
        this.Body2.setRotationPoint(0.0F, -0.1F, 8.0F);
        this.Body2.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 8, 0.0F);
        this.Body3 = new Cuboid(this, 0, 17);
        this.Body3.setRotationPoint(0.0F, 0.1F, 8.0F);
        this.Body3.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 8, 0.0F);
        this.Body6 = new Cuboid(this, 22, 27);
        this.Body6.setRotationPoint(0.0F, -0.1F, 8.0F);
        this.Body6.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 8, 0.0F);
        this.Body4 = new Cuboid(this, 22, 17);
        this.Body4.setRotationPoint(0.0F, -0.1F, 8.0F);
        this.Body4.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 8, 0.0F);
        this.Body5 = new Cuboid(this, 0, 27);
        this.Body5.setRotationPoint(0.0F, 0.1F, 8.0F);
        this.Body5.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 8, 0.0F);
        this.Tongue = new Cuboid(this, 16, 0);
        this.Tongue.setRotationPoint(0.0F, 1.5F, -4.0F);
        this.Tongue.addBox(-1.5F, -0.5F, -3.5F, 3, 1, 4, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 22.75F, -22.0F);
        this.Head.addBox(-2.0F, -1.5F, -4.0F, 4, 3, 4, 0.0F);
        this.Body1 = new Cuboid(this, 0, 7);
        this.Body1.setRotationPoint(0.0F, 0.25F, 0.0F);
        this.Body1.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 8, 0.0F);
        this.Body1.addChild(this.Body2);
        this.Body2.addChild(this.Body3);
        this.Body5.addChild(this.Body6);
        this.Body3.addChild(this.Body4);
        this.Body4.addChild(this.Body5);
        this.Head.addChild(this.Tongue);
        this.Head.addChild(this.Body1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Head.render(f5);
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
