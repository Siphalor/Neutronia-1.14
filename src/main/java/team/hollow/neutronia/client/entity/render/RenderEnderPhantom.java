package team.hollow.neutronia.client.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.entity.EntityEnderPhantom;

public class RenderEnderPhantom extends MobEntityRenderer<EntityEnderPhantom, PhantomEntityModel<EntityEnderPhantom>> {

    public static final Identifier SCORP_TEXTURE = new Identifier(Neutronia.MOD_ID + ":textures/entity/phantom/ender_phantom.png");

    public RenderEnderPhantom(EntityRenderDispatcher manager) {
        super(manager, new PhantomEntityModel<>(), 0.5F);
    }

    @Override
    protected Identifier getTexture(EntityEnderPhantom entity) {
        return SCORP_TEXTURE;
    }

}
