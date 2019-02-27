package team.hollow.neutronia.client.entity.render.model.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * Firefly - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelFirefly extends EntityModel {
    public Cuboid Glow;
    public Cuboid LeftWing;
    public Cuboid RightWing;
    public Cuboid Body;
    public Cuboid Head;

    public ModelFirefly() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.LeftWing = new Cuboid(this, 6, 0);
        this.LeftWing.setRotationPoint(-0.5F, 21.0F, 1.5F);
        this.LeftWing.addBox(-1.5F, 0.0F, 0.0F, 3, 0, 4, 0.0F);
        this.setRotateAngle(LeftWing, -0.6108652381980153F, -0.7853981633974483F, 1.1344640137963142F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.2F, 0.9F);
        this.Head.addBox(-1.5F, -1.0F, -2.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(Head, -0.6981317007977318F, 0.0F, 0.0F);
        this.Body = new Cuboid(this, 0, 4);
        this.Body.setRotationPoint(0.0F, 20.5F, 0.25F);
        this.Body.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(Body, 0.6981317007977318F, 0.0F, 0.0F);
        this.Glow = new Cuboid(this, 8, 4);
        this.Glow.setRotationPoint(0.0F, 23.0F, 2.5F);
        this.Glow.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.5F);
        this.setRotateAngle(Glow, 0.6981317007977318F, 0.0F, 0.0F);
        this.RightWing = new Cuboid(this, 6, 0);
        this.RightWing.mirror = true;
        this.RightWing.setRotationPoint(0.5F, 21.0F, 1.5F);
        this.RightWing.addBox(-1.5F, 0.0F, 0.0F, 3, 0, 4, 0.0F);
        this.setRotateAngle(RightWing, -0.6108652381980153F, 0.7853981633974483F, -1.1344640137963142F);
        this.Body.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
        this.LeftWing.render(f5);
        GlStateManager.disableBlend();
        this.Body.render(f5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
        this.Glow.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
        this.RightWing.render(f5);
        GlStateManager.disableBlend();
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
