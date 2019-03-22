package team.hollow.neutronia.client.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.client.entity.render.model.model.ModelJungleFrog;
import team.hollow.neutronia.entity.EntityJungleFrog;

@Environment(EnvType.CLIENT)
public class JungleFrogEntityRenderer extends MobEntityRenderer<EntityJungleFrog, ModelJungleFrog<EntityJungleFrog>> {
    private static final Identifier SKIN = new Identifier(Neutronia.MOD_ID, "textures/entity/jungle_frog.png");

    public JungleFrogEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new ModelJungleFrog<>(), 0.7F);
    }

    protected Identifier getTexture(EntityJungleFrog grizzlyBearEntity_1) {
        return SKIN;
    }

}