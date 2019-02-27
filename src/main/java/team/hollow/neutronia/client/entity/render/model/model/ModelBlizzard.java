package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Blizzard - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelBlizzard extends EntityModel {
    public Cuboid Body;
    public Cuboid SpikeTop;
    public Cuboid SpikeBottom;
    public Cuboid RightSpike;
    public Cuboid LeftSpike;
    public Cuboid TopRightSpike;
    public Cuboid TopLeftSpike;
    public Cuboid BottomRightSpike;
    public Cuboid BottomLeftSpike;
    public Cuboid Pupil;

    public ModelBlizzard() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.SpikeTop = new Cuboid(this, 0, 16);
        this.SpikeTop.setRotationPoint(0.0F, -5.0F, -0.9F);
        this.SpikeTop.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(SpikeTop, 0.08726646259971647F, 0.0F, 0.0F);
        this.Pupil = new Cuboid(this, 24, 28);
        this.Pupil.setRotationPoint(0.0F, -16.5F, -2.25F);
        this.Pupil.addBox(-0.5F, 15.0F, 0.0F, 1, 3, 1, 0.0F);
        this.LeftSpike = new Cuboid(this, 8, 28);
        this.LeftSpike.mirror = true;
        this.LeftSpike.setRotationPoint(5.0F, 0.0F, -0.9F);
        this.LeftSpike.addBox(0.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(LeftSpike, 0.0F, 0.09599310885968812F, 0.0F);
        this.RightSpike = new Cuboid(this, 8, 28);
        this.RightSpike.setRotationPoint(-5.0F, 0.0F, -0.9F);
        this.RightSpike.addBox(-6.0F, -1.0F, -1.0F, 6, 2, 2, 0.0F);
        this.setRotateAngle(RightSpike, 0.0F, -0.09599310885968812F, 0.0F);
        this.SpikeBottom = new Cuboid(this, 0, 24);
        this.SpikeBottom.setRotationPoint(0.0F, 5.0F, -0.9F);
        this.SpikeBottom.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(SpikeBottom, -0.08726646259971647F, 0.0F, 0.0F);
        this.BottomRightSpike = new Cuboid(this, 18, 20);
        this.BottomRightSpike.setRotationPoint(-4.75F, 4.75F, 0.9F);
        this.BottomRightSpike.addBox(-1.5F, 0.0F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(BottomRightSpike, 0.08726646259971647F, 0.0F, 0.7853981633974483F);
        this.BottomLeftSpike = new Cuboid(this, 18, 20);
        this.BottomLeftSpike.mirror = true;
        this.BottomLeftSpike.setRotationPoint(4.75F, 4.75F, 0.9F);
        this.BottomLeftSpike.addBox(-1.5F, 0.0F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(BottomLeftSpike, 0.08726646259971647F, 0.0F, -0.7853981633974483F);
        this.Body = new Cuboid(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 13.0F, 0.0F);
        this.Body.addBox(-6.0F, -6.0F, -2.0F, 12, 12, 4, 0.0F);
        this.TopLeftSpike = new Cuboid(this, 8, 20);
        this.TopLeftSpike.mirror = true;
        this.TopLeftSpike.setRotationPoint(4.75F, -4.75F, 0.9F);
        this.TopLeftSpike.addBox(-1.5F, -6.0F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(TopLeftSpike, -0.08726646259971647F, 0.0F, 0.7853981633974483F);
        this.TopRightSpike = new Cuboid(this, 8, 20);
        this.TopRightSpike.setRotationPoint(-4.75F, -4.75F, 0.9F);
        this.TopRightSpike.addBox(-1.5F, -6.0F, -1.0F, 3, 6, 2, 0.0F);
        this.setRotateAngle(TopRightSpike, -0.08726646259971647F, 0.0F, -0.7853981633974483F);
        this.Body.addChild(this.SpikeTop);
        this.Body.addChild(this.Pupil);
        this.Body.addChild(this.LeftSpike);
        this.Body.addChild(this.RightSpike);
        this.Body.addChild(this.SpikeBottom);
        this.Body.addChild(this.BottomRightSpike);
        this.Body.addChild(this.BottomLeftSpike);
        this.Body.addChild(this.TopLeftSpike);
        this.Body.addChild(this.TopRightSpike);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
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
