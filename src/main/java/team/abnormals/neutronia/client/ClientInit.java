package team.abnormals.neutronia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import team.abnormals.neutronia.blocks.entity.SignBlockEntity;
import team.abnormals.neutronia.blocks.entity.StoneChestBlockEntity;
import team.abnormals.neutronia.client.entity.render.*;
import team.abnormals.neutronia.client.renderer.SignBlockEntityRenderer;
import team.abnormals.neutronia.client.renderer.StoneChestBlockEntityRenderer;
import team.abnormals.neutronia.entity.SocialVillager;
import team.abnormals.neutronia.entity.passive.BlackBearEntity;
import team.abnormals.neutronia.entity.passive.BrownBearEntity;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

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