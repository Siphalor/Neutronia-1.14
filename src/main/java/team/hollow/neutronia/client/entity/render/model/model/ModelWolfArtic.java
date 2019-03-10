package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

/**
 * ModelWolfArtic - AguilaDaddy
 * Created using Tabula 7.0.0
 */
public class ModelWolfArtic<T extends LivingEntity> extends EntityModel<T> {
    public Cuboid field_78179_f;
    public Cuboid field_78186_h;
    public Cuboid field_78183_b;
    public Cuboid field_78181_d;
    public Cuboid field_78184_c;
    public Cuboid field_78182_e;
    public Cuboid field_78180_g;
    public Cuboid field_78185_a0;
    public Cuboid field_78185_a1;
    public Cuboid field_78185_a2;
    public Cuboid field_78185_a3;

    public ModelWolfArtic() {
        this.textureWidth = 66;
        this.textureHeight = 34;
        this.field_78185_a2 = new Cuboid(this, 15, 14);
        this.field_78185_a2.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a2.addBox(2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.field_78185_a3 = new Cuboid(this, 0, 10);
        this.field_78185_a3.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a3.addBox(-0.5F, 0.0F, -5.0F, 3, 3, 4, 0.0F);
        this.field_78183_b = new Cuboid(this, 18, 17);
        this.field_78183_b.setRotationPoint(0.0F, 15.0F, 2.0F);
        this.field_78183_b.addBox(-3.0F, -2.0F, -3.0F, 6, 9, 7, 0.0F);
        this.setRotateAngle(field_78183_b, 1.5707963267948966F, 0.0F, 0.0F);
        this.field_78185_a0 = new Cuboid(this, 0, 0);
        this.field_78185_a0.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a0.addBox(-2.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.field_78179_f = new Cuboid(this, 0, 18);
        this.field_78179_f.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.field_78179_f.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78181_d = new Cuboid(this, 0, 18);
        this.field_78181_d.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.field_78181_d.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78185_a1 = new Cuboid(this, 15, 14);
        this.field_78185_a1.setRotationPoint(-1.0F, 13.5F, -7.0F);
        this.field_78185_a1.addBox(-2.0F, -5.0F, 0.0F, 2, 2, 1, 0.0F);
        this.field_78180_g = new Cuboid(this, 9, 18);
        this.field_78180_g.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.field_78180_g.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78184_c = new Cuboid(this, 0, 18);
        this.field_78184_c.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.field_78184_c.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.field_78186_h = new Cuboid(this, 21, 0);
        this.field_78186_h.setRotationPoint(-1.0F, 15.0F, -3.0F);
        this.field_78186_h.addBox(-3.0F, -3.0F, -3.0F, 8, 9, 8, 0.0F);
        this.setRotateAngle(field_78186_h, 1.5707963267948966F, 0.0F, 0.0F);
        this.field_78182_e = new Cuboid(this, 0, 18);
        this.field_78182_e.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.field_78182_e.addBox(0.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.field_78185_a2.render(f5);
        this.field_78185_a3.render(f5);
        this.field_78183_b.render(f5);
        this.field_78185_a0.render(f5);
        this.field_78179_f.render(f5);
        this.field_78181_d.render(f5);
        this.field_78185_a1.render(f5);
        this.field_78180_g.render(f5);
        this.field_78184_c.render(f5);
        this.field_78186_h.render(f5);
        this.field_78182_e.render(f5);
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
