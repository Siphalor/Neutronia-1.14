package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.ResourceLocation;

public class ModelTrident extends EntityModel {
    public static final ResourceLocation field_203080_a = new ResourceLocation("textures/entity/trident.png");
    private final Cuboid field_203081_b;

    public ModelTrident() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.field_203081_b = new Cuboid(this, 0, 0);
        this.field_203081_b.addBox(-0.5F, -4.0F, -0.5F, 1, 31, 1, 0.0F);
        Cuboid Cuboid = new Cuboid(this, 4, 0);
        Cuboid.addBox(-1.5F, 0.0F, -0.5F, 3, 2, 1);
        this.field_203081_b.addChild(Cuboid);
        Cuboid Cuboid1 = new Cuboid(this, 4, 3);
        Cuboid1.addBox(-2.5F, -3.0F, -0.5F, 1, 4, 1);
        this.field_203081_b.addChild(Cuboid1);
        Cuboid Cuboid2 = new Cuboid(this, 4, 3);
        Cuboid2.mirror = true;
        Cuboid2.addBox(1.5F, -3.0F, -0.5F, 1, 4, 1);
        this.field_203081_b.addChild(Cuboid2);
    }

    public void func_203079_a() {
        this.field_203081_b.render(0.0625F);
    }
}
