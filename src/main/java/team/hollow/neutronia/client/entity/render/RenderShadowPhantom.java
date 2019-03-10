package team.hollow.neutronia.client.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.entity.EntityShadowPhantom;

public class RenderShadowPhantom extends MobEntityRenderer<EntityShadowPhantom, PhantomEntityModel<EntityShadowPhantom>> {

    public static final Identifier SCORP_TEXTURE = new Identifier(Neutronia.MOD_ID + ":textures/entity/phantom/shadow_phantom.png");

    public RenderShadowPhantom(EntityRenderDispatcher manager) {
        super(manager, new PhantomEntityModel<>(), 0.5F);
    }

    @Override
    protected Identifier getTexture(EntityShadowPhantom entity) {
        return SCORP_TEXTURE;
    }

}