package team.hollow.neutronia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import team.hollow.neutronia.blocks.entity.SignBlockEntity;
import team.hollow.neutronia.blocks.entity.StoneChestBlockEntity;
import team.hollow.neutronia.client.entity.render.BlackBearEntityRenderer;
import team.hollow.neutronia.client.entity.render.BrownBearEntityRenderer;
import team.hollow.neutronia.client.entity.render.SocialVillagerRenderer;
import team.hollow.neutronia.client.entity.render.VillagerEntityRenderer;
import team.hollow.neutronia.client.renderer.SignBlockEntityRenderer;
import team.hollow.neutronia.client.renderer.StoneChestBlockEntityRenderer;
import team.hollow.neutronia.entity.SocialVillager;
import team.hollow.neutronia.entity.passive.BlackBearEntity;
import team.hollow.neutronia.entity.passive.BrownBearEntity;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(VillagerPlusEntity.class, (entityRenderDispatcher, context) ->
                new VillagerEntityRenderer(entityRenderDispatcher, context.getResourceManager()));
        BlockEntityRendererRegistry.INSTANCE.register(StoneChestBlockEntity.class, new StoneChestBlockEntityRenderer());
        BlockEntityRendererRegistry.INSTANCE.register(SignBlockEntity.class, new SignBlockEntityRenderer());

        EntityRendererRegistry.INSTANCE.register(BrownBearEntity.class, (entityRenderDispatcher, context) ->
                new BrownBearEntityRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(BlackBearEntity.class, (entityRenderDispatcher, context) ->
                new BlackBearEntityRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(SocialVillager.class, (entityRenderDispatcher, context) ->
                new SocialVillagerRenderer(entityRenderDispatcher));
    }
}