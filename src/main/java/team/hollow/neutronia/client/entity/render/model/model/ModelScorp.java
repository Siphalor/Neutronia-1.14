package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelSpider - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelScorp extends EntityModel {

    public Cuboid field_78210_j;
    public Cuboid field_78209_a;
    public Cuboid field_78208_c;
    public Cuboid field_78206_e;
    public Cuboid field_78204_g;
    public Cuboid field_78213_i;
    public Cuboid field_78211_k;
    public Cuboid field_78203_f;
    private Cuboid field_78207_b;
    private Cuboid field_78205_d;
    private Cuboid field_78212_h;

    public ModelScorp() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.field_78210_j = new Cuboid(this, 18, 0);
        this.field_78210_j.setRotationPoint(-4.0F, 15.0F, -1.0F);
        this.field_78210_j.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78210_j, 0.0F, -0.7853981852531433F, -0.7853981852531433F);
        this.field_78209_a = new Cuboid(this, 32, 4);
        this.field_78209_a.setRotationPoint(0.0F, 15.0F, -3.0F);
        this.field_78209_a.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.field_78211_k = new Cuboid(this, 18, 0);
        this.field_78211_k.setRotationPoint(4.0F, 15.0F, -1.0F);
        this.field_78211_k.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78211_k, 0.0F, 0.7853981852531433F, 0.7853981852531433F);
        this.field_78213_i = new Cuboid(this, 18, 0);
        this.field_78213_i.setRotationPoint(4.0F, 15.0F, 0.0F);
        this.field_78213_i.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78213_i, 0.0F, 0.39269909262657166F, 0.5811946392059326F);
        this.field_78205_d = new Cuboid(this, 18, 0);
        this.field_78205_d.setRotationPoint(-4.0F, 15.0F, 2.0F);
        this.field_78205_d.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78205_d, 0.0F, 0.7853981852531433F, -0.7853981852531433F);
        this.field_78203_f = new Cuboid(this, 18, 0);
        this.field_78203_f.setRotationPoint(-4.0F, 15.0F, 1.0F);
        this.field_78203_f.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78203_f, 0.0F, 0.39269909262657166F, -0.5811946392059326F);
        this.field_78212_h = new Cuboid(this, 18, 0);
        this.field_78212_h.setRotationPoint(-4.0F, 15.0F, 0.0F);
        this.field_78212_h.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78212_h, 0.0F, -0.39269909262657166F, -0.5811946392059326F);
        this.field_78206_e = new Cuboid(this, 18, 0);
        this.field_78206_e.setRotationPoint(4.0F, 15.0F, 2.0F);
        this.field_78206_e.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78206_e, 0.0F, -0.7853981852531433F, 0.7853981852531433F);
        this.field_78207_b = new Cuboid(this, 0, 0);
        this.field_78207_b.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.field_78207_b.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.field_78208_c = new Cuboid(this, 0, 12);
        this.field_78208_c.setRotationPoint(0.0F, 15.0F, 9.0F);
        this.field_78208_c.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
        this.field_78204_g = new Cuboid(this, 18, 0);
        this.field_78204_g.setRotationPoint(4.0F, 15.0F, 1.0F);
        this.field_78204_g.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(field_78204_g, 0.0F, -0.39269909262657166F, 0.5811946392059326F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.field_78210_j.render(f5);
        this.field_78209_a.render(f5);
        this.field_78211_k.render(f5);
        this.field_78213_i.render(f5);
        this.field_78205_d.render(f5);
        this.field_78203_f.render(f5);
        this.field_78212_h.render(f5);
        this.field_78206_e.render(f5);
        this.field_78207_b.render(f5);
        this.field_78208_c.render(f5);
        this.field_78204_g.render(f5);
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
