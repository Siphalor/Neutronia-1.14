package team.hollow.neutronia.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.client.entity.render.model.BrownBearEntityModel;
import team.hollow.neutronia.entity.passive.BrownBearEntity;

@Environment(EnvType.CLIENT)
public class BrownBearEntityRenderer extends MobEntityRenderer<BrownBearEntity, BrownBearEntityModel<BrownBearEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/bear/brownbear.png");

    public BrownBearEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new BrownBearEntityModel<>(), 0.7F);
    }

    protected Identifier getTexture(BrownBearEntity brownBearEntity_1) {
        return SKIN;
    }

    protected void method_4099(BrownBearEntity brownBearEntity_1, float float_1) {
        GlStateManager.scalef(1.2F, 1.2F, 1.2F);
        super.method_4042(brownBearEntity_1, float_1);
    }

}