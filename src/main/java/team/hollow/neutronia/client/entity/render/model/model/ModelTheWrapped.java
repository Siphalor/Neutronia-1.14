package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * The Wrapped -
 * Created using Tabula 7.0.0
 */
public class ModelTheWrapped extends EntityModel {
    public Cuboid UpperTorso;
    public Cuboid LowerTorso;
    public Cuboid Head;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;

    public ModelTheWrapped() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(Head, -0.2617993877991494F, 0.0F, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 16);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(1.9F, 2.0F, 0.1F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftLeg, -0.17453292519943295F, 0.0F, 0.0F);
        this.LowerTorso = new Cuboid(this, 32, 10);
        this.LowerTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LowerTorso.addBox(-4.0F, 0.0F, -2.0F, 8, 2, 4, 0.0F);
        this.setRotateAngle(LowerTorso, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 16);
        this.RightLeg.setRotationPoint(-1.9F, 2.0F, 0.1F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightLeg, -0.17453292519943295F, 0.0F, 0.0F);
        this.UpperTorso = new Cuboid(this, 16, 16);
        this.UpperTorso.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.UpperTorso.addBox(-6.0F, -10.0F, -3.0F, 12, 10, 6, 0.0F);
        this.setRotateAngle(UpperTorso, 0.17453292519943295F, 0.0F, 0.0F);
        this.UpperTorso.addChild(this.Head);
        this.UpperTorso.addChild(this.LeftLeg);
        this.UpperTorso.addChild(this.LowerTorso);
        this.UpperTorso.addChild(this.RightLeg);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.UpperTorso.render(f5);
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
