package team.hollow.neutronia.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.client.entity.render.model.WolfEntityModel;
import team.hollow.neutronia.entity.ArcticWolfEntity;

import static team.hollow.neutronia.Neutronia.MOD_ID;

public class ArcticWolfEntityRenderer extends MobEntityRenderer<ArcticWolfEntity, WolfEntityModel<ArcticWolfEntity>> {
    private static final Identifier FOX_TEXTURES = new Identifier(MOD_ID, "textures/entity/arctic_wolf.png");

    public ArcticWolfEntityRenderer(EntityRenderDispatcher p_i47187_1_) {
        super(p_i47187_1_, new WolfEntityModel<>(), 0.5F);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float method_4045(ArcticWolfEntity livingBase, float partialTicks) {
        return livingBase.method_6714();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void render(ArcticWolfEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isWet()) {
            float f = entity.getBrightnessAtEyes() * entity.method_6707(partialTicks);
            GlStateManager.color3f(f, f, f);
        }
        super.render(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected Identifier getTexture(ArcticWolfEntity entity) {
        return FOX_TEXTURES;
    }

}