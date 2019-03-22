package team.hollow.neutronia.client.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.entity.EntityBloodPhantom;

public class RenderRedPhantom extends MobEntityRenderer<EntityBloodPhantom, PhantomEntityModel<EntityBloodPhantom>> {

    public static final Identifier SCORP_TEXTURE = new Identifier(Neutronia.MOD_ID + ":textures/entity/phantom/blood_phantom.png");

    public RenderRedPhantom(EntityRenderDispatcher manager) {
        super(manager, new PhantomEntityModel<>(), 0.5F);
    }

    @Override
    protected Identifier getTexture(EntityBloodPhantom entity) {
        return SCORP_TEXTURE;
    }

}
