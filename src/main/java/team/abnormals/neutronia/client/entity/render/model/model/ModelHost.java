package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * host - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelHost extends EntityModel {
    public Cuboid head;
    public Cuboid jaw;

    public ModelHost() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new Cuboid(this, 0, 0);
        this.head.setRotationPoint(0.0F, 16.0F, -0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.jaw = new Cuboid(this, 0, 16);
        this.jaw.setRotationPoint(0.0F, 16.0F, -0.0F);
        this.jaw.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.jaw.render(f5);
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
