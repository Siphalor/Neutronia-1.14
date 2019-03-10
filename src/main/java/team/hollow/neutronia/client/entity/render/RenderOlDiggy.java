package team.hollow.neutronia.client.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.client.entity.render.model.model.ModelOlDiggy;
import team.hollow.neutronia.entity.EntityOlDiggy;

public class RenderOlDiggy extends MobEntityRenderer<EntityOlDiggy, ModelOlDiggy<EntityOlDiggy>> {

    public static final Identifier SCORP_TEXTURE = new Identifier(Neutronia.MOD_ID + ":textures/entity/ol_diggy.png");

    public RenderOlDiggy(EntityRenderDispatcher manager) {
        super(manager, new ModelOlDiggy(), 0.5F);
    }

    @Override
    protected Identifier getTexture(EntityOlDiggy var1) {
        return SCORP_TEXTURE;
    }

}
