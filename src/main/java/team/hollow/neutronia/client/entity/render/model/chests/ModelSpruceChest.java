package team.hollow.neutronia.client.entity.render.model.chests;

import net.minecraft.client.model.Cuboid;
import team.hollow.neutronia.client.entity.render.model.BaseChestModel;

/**
 * ModelSpruceChest - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelSpruceChest extends BaseChestModel {
    public Cuboid Lid;
    public Cuboid Box;
    public Cuboid Handle;

    public ModelSpruceChest() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Handle = new Cuboid(this, 0, 0);
        this.Handle.setRotationPoint(7.0F, -1.0F, -14.0F);
        this.Handle.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 0, 0.0F);
        this.setRotateAngle(Handle, -0.2181661564992912F, 0.0F, 0.0F);
        this.Box = new Cuboid(this, 0, 19);
        this.Box.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.Box.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
        this.Lid = new Cuboid(this, 0, 0);
        this.Lid.setRotationPoint(1.0F, 7.0F, 15.0F);
        this.Lid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
        this.Lid.addChild(this.Handle);
    }

    @Override
    public void render() {
        this.Box.render(0.0625F);
        this.Lid.render(0.0625F);
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
