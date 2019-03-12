package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * MammothShaved - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelMammoth extends EntityModel {
    public Cuboid furbody1;
    public Cuboid armleft;
    public Cuboid furhead;
    public Cuboid body2;
    public Cuboid legright;
    public Cuboid armright;
    public Cuboid legleft;
    public Cuboid furbody2;
    public Cuboid head;
    public Cuboid body1;
    public Cuboid tail;
    public Cuboid trunk1;
    public Cuboid earleft;
    public Cuboid earright;
    public Cuboid tuskright;
    public Cuboid tuskleft;
    public Cuboid trunk2;
    public Cuboid trunk3;

    public ModelMammoth() {
        this.textureWidth = 150;
        this.textureHeight = 100;
        this.tail = new Cuboid(this, 29, 0);
        this.tail.setRotationPoint(3.0F, 1.0F, 5.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 2, 9, 2, 0.0F);
        this.furbody1 = new Cuboid(this, 26, 53);
        this.furbody1.setRotationPoint(-6.0F, 5.0F, -7.0F);
        this.furbody1.addBox(0.0F, 0.0F, 0.0F, 12, 15, 15, 0.0F);
        this.legright = new Cuboid(this, 53, 22);
        this.legright.setRotationPoint(-4.5F, 12.0F, 8.0F);
        this.legright.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.trunk1 = new Cuboid(this, 0, 17);
        this.trunk1.setRotationPoint(1.5F, 3.0F, -1.0F);
        this.trunk1.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.setRotateAngle(trunk1, -0.136659280431156F, 0.0F, 0.0F);
        this.armright = new Cuboid(this, 53, 38);
        this.armright.setRotationPoint(-4.5F, 13.0F, -4.0F);
        this.armright.addBox(0.0F, 0.0F, 0.0F, 4, 11, 4, 0.0F);
        this.furbody2 = new Cuboid(this, 98, 0);
        this.furbody2.setRotationPoint(-5.0F, 7.0F, 5.0F);
        this.furbody2.addBox(0.0F, 0.0F, 1.0F, 10, 13, 7, 0.0F);
        this.head = new Cuboid(this, 0, 0);
        this.head.setRotationPoint(-3.0F, 3.0F, -10.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 6, 10, 7, 0.0F);
        this.armleft = new Cuboid(this, 53, 38);
        this.armleft.setRotationPoint(0.5F, 13.0F, -4.0F);
        this.armleft.addBox(0.0F, 0.0F, 0.0F, 4, 11, 4, 0.0F);
        this.furhead = new Cuboid(this, 80, 69);
        this.furhead.setRotationPoint(-4.0F, 1.5F, -11.5F);
        this.furhead.addBox(0.0F, 0.0F, 0.0F, 8, 5, 9, 0.0F);
        this.legleft = new Cuboid(this, 53, 22);
        this.legleft.setRotationPoint(0.5F, 12.0F, 8.0F);
        this.legleft.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.body2 = new Cuboid(this, 68, 0);
        this.body2.setRotationPoint(-4.0F, 7.5F, 5.5F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 8, 9, 7, 0.0F);
        this.tuskleft = new Cuboid(this, 0, 31);
        this.tuskleft.setRotationPoint(5.0F, 8.0F, -10.0F);
        this.tuskleft.addBox(0.0F, 0.0F, 0.0F, 0, 10, 13, 0.0F);
        this.setRotateAngle(tuskleft, 0.0F, 0.0F, -0.27314402793711257F);
        this.trunk2 = new Cuboid(this, 0, 28);
        this.trunk2.setRotationPoint(0.0F, 8.0F, 0.2F);
        this.trunk2.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(trunk2, 0.31869712141416456F, 0.0F, 0.0F);
        this.body1 = new Cuboid(this, 26, 0);
        this.body1.setRotationPoint(-5.0F, 5.5F, -5.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 10, 11, 11, 0.0F);
        this.earright = new Cuboid(this, 23, 0);
        this.earright.setRotationPoint(-1.5F, 3.0F, 3.0F);
        this.earright.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(earright, 0.0F, 0.6373942428283291F, 0.011868238913561441F);
        this.earleft = new Cuboid(this, 57, 0);
        this.earleft.setRotationPoint(6.0F, 3.0F, 2.0F);
        this.earleft.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(earleft, 0.0F, -0.6373942428283291F, 0.0F);
        this.tuskright = new Cuboid(this, 0, 31);
        this.tuskright.setRotationPoint(1.0F, 8.0F, -11.0F);
        this.tuskright.addBox(0.0F, 0.0F, 0.0F, 0, 10, 13, 0.0F);
        this.setRotateAngle(tuskright, 0.0F, 0.0F, 0.27314402793711257F);
        this.trunk3 = new Cuboid(this, 0, 36);
        this.trunk3.setRotationPoint(0.5F, 5.0F, 1.0F);
        this.trunk3.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(trunk3, 0.31869712141416456F, 0.0F, 0.0F);
        this.body2.addChild(this.tail);
        this.head.addChild(this.trunk1);
        this.head.addChild(this.tuskleft);
        this.trunk1.addChild(this.trunk2);
        this.head.addChild(this.earright);
        this.head.addChild(this.earleft);
        this.head.addChild(this.tuskright);
        this.trunk2.addChild(this.trunk3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.furbody1.render(f5);
        this.legright.render(f5);
        this.armright.render(f5);
        this.furbody2.render(f5);
        this.head.render(f5);
        this.armleft.render(f5);
        this.furhead.render(f5);
        this.legleft.render(f5);
        this.body2.render(f5);
        this.body1.render(f5);
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
