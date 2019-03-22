package team.hollow.neutronia.client.entity.render.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import team.hollow.neutronia.entity.passive.GrizzlyBearEntity;

@Environment(EnvType.CLIENT)
public class GrizzlyBearEntityModel<T extends GrizzlyBearEntity> extends QuadrupedEntityModel<T> {
    public GrizzlyBearEntityModel() {
        super(12, 0.0F);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new Cuboid(this, 0, 0);
        this.head.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
        this.head.setRotationPoint(0.0F, 10.0F, -16.0F);
        this.head.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F);
        this.head.setTextureOffset(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        Cuboid cuboid_1 = this.head.setTextureOffset(26, 0);
        cuboid_1.mirror = true;
        cuboid_1.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F);
        this.body = new Cuboid(this);
        this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);
        this.leg1 = new Cuboid(this, 50, 22);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg1.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.leg2 = new Cuboid(this, 50, 22);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg2.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.leg3 = new Cuboid(this, 50, 40);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg3.setRotationPoint(-2.5F, 14.0F, -7.0F);
        this.leg4 = new Cuboid(this, 50, 40);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg4.setRotationPoint(2.5F, 14.0F, -7.0F);
        --this.leg1.rotationPointX;
        ++this.leg2.rotationPointX;
        Cuboid var10000 = this.leg1;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.leg2;
        var10000.rotationPointZ += 0.0F;
        --this.leg3.rotationPointX;
        ++this.leg4.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg4.rotationPointZ;
        this.field_3537 += 2.0F;
    }

    public void render(T polarBearEntity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
        this.setAngles(polarBearEntity_1, float_1, float_2, float_3, float_4, float_5, float_6);
        if (this.isChild) {
            this.field_3540 = 16.0F;
            this.field_3537 = 4.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.6666667F, 0.6666667F, 0.6666667F);
            GlStateManager.translatef(0.0F, this.field_3540 * float_6, this.field_3537 * float_6);
            this.head.render(float_6);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * float_6, 0.0F);
            this.body.render(float_6);
            this.leg1.render(float_6);
            this.leg2.render(float_6);
            this.leg3.render(float_6);
            this.leg4.render(float_6);
            GlStateManager.popMatrix();
        } else {
            this.head.render(float_6);
            this.body.render(float_6);
            this.leg1.render(float_6);
            this.leg2.render(float_6);
            this.leg3.render(float_6);
            this.leg4.render(float_6);
        }

    }

    public void setAngles(T polarBearEntity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
        super.setAngles(polarBearEntity_1, float_1, float_2, float_3, float_4, float_5, float_6);
        float float_7 = float_3 - (float) polarBearEntity_1.age;
        float float_8 = polarBearEntity_1.method_6601(float_7);
        float_8 *= float_8;
        float float_9 = 1.0F - float_8;
        this.body.pitch = 1.5707964F - float_8 * 3.1415927F * 0.35F;
        this.body.rotationPointY = 9.0F * float_9 + 11.0F * float_8;
        this.leg3.rotationPointY = 14.0F * float_9 - 6.0F * float_8;
        this.leg3.rotationPointZ = -8.0F * float_9 - 4.0F * float_8;
        Cuboid var10000 = this.leg3;
        var10000.pitch -= float_8 * 3.1415927F * 0.45F;
        this.leg4.rotationPointY = this.leg3.rotationPointY;
        this.leg4.rotationPointZ = this.leg3.rotationPointZ;
        var10000 = this.leg4;
        var10000.pitch -= float_8 * 3.1415927F * 0.45F;
        this.head.rotationPointY = 10.0F * float_9 - 12.0F * float_8;
        this.head.rotationPointZ = -16.0F * float_9 - 3.0F * float_8;
        var10000 = this.head;
        var10000.pitch += float_8 * 3.1415927F * 0.15F;
    }

}
