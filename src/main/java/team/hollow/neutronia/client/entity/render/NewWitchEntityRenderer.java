package team.hollow.neutronia.client.entity.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.WitchHeldItemFeatureRenderer;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.client.entity.render.model.WitchModel;

public class NewWitchEntityRenderer extends MobEntityRenderer<WitchEntity, WitchModel> {

    private static Identifier SKIN = new Identifier("minecraft", "textures/entity/witch.png");

    public NewWitchEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1, new WitchModel(), 0.5F);
        this.addFeature(new WitchHeldItemFeatureRenderer(this));
    }

    @Override
    protected Identifier getTexture(WitchEntity var1) {
        return SKIN;
    }

}
