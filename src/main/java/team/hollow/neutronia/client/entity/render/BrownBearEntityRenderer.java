package team.hollow.neutronia.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.client.entity.render.model.GrizzlyBearEntityModel;
import team.hollow.neutronia.entity.passive.GrizzlyBearEntity;

@Environment(EnvType.CLIENT)
public class BrownBearEntityRenderer extends MobEntityRenderer<GrizzlyBearEntity, GrizzlyBearEntityModel<GrizzlyBearEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/bear/brownbear.png");

    public BrownBearEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new GrizzlyBearEntityModel<>(), 0.7F);
    }

    protected Identifier getTexture(GrizzlyBearEntity grizzlyBearEntity_1) {
        return SKIN;
    }

    protected void method_4099(GrizzlyBearEntity grizzlyBearEntity_1, float float_1) {
        GlStateManager.scalef(1.2F, 1.2F, 1.2F);
        super.method_4042(grizzlyBearEntity_1, float_1);
    }

}