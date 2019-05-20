package team.hollow.neutronia.client.entity.render.model.chests;

import net.minecraft.client.model.Cuboid;

/**
 * ModelLargeSpruceChest - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelLargeSpruceChest extends ModelSpruceChest {
    
    public ModelLargeSpruceChest() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.hatch = new Cuboid(this, 0, 0);
        this.hatch.setRotationPoint(15.0F, -1.0F, -14.0F);
        this.hatch.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);
        this.setRotateAngle(hatch, -0.2181661564992912F, 0.0F, 0.0F);
        this.lid = new Cuboid(this, 0, 0);
        this.lid.setRotationPoint(1.0F, 7.0F, 15.0F);
        this.lid.addBox(0.0F, -5.0F, -14.0F, 30, 5, 14, 0.0F);
        this.base = new Cuboid(this, 0, 19);
        this.base.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 30, 10, 14, 0.0F);
        this.lid.addChild(this.hatch);
    }

    @Override
    public void render() {
        this.lid.render(0.0625F);
        this.base.render(0.0625F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
    }
}
