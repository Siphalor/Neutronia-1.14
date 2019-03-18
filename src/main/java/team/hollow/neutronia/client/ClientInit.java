package team.hollow.neutronia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import team.hollow.neutronia.blocks.entity.SignBlockEntity;
import team.hollow.neutronia.blocks.entity.StoneChestBlockEntity;
import team.hollow.neutronia.client.entity.render.*;
import team.hollow.neutronia.client.renderer.SignBlockEntityRenderer;
import team.hollow.neutronia.client.renderer.StoneChestBlockEntityRenderer;
import team.hollow.neutronia.entity.*;
import team.hollow.neutronia.entity.passive.BlackBearEntity;
import team.hollow.neutronia.entity.passive.BrownBearEntity;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(VillagerPlusEntity.class, (manager, context) ->
                new VillagerEntityRenderer(manager, context.getResourceManager()));
        BlockEntityRendererRegistry.INSTANCE.register(StoneChestBlockEntity.class, new StoneChestBlockEntityRenderer());
        BlockEntityRendererRegistry.INSTANCE.register(SignBlockEntity.class, new SignBlockEntityRenderer());

        EntityRendererRegistry.INSTANCE.register(BrownBearEntity.class, (manager, context) ->
                new BrownBearEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(BlackBearEntity.class, (manager, context) ->
                new BlackBearEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(SocialVillager.class, (manager, context) ->
                new SocialVillagerRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityOlDiggy.class, (manager, context) ->
                new RenderOlDiggy(manager));
        EntityRendererRegistry.INSTANCE.register(EntityShadowPhantom.class, (manager, context) ->
                new RenderShadowPhantom(manager));
        EntityRendererRegistry.INSTANCE.register(EntityEnderPhantom.class, (manager, context) ->
                new RenderEnderPhantom(manager));
        EntityRendererRegistry.INSTANCE.register(EntityBloodPhantom.class, (manager, context) ->
                new RenderBloodPhantom(manager));
        EntityRendererRegistry.INSTANCE.register(ArcticWolfEntity.class, (manager, context) ->
                new ArcticWolfEntityRenderer(manager));
    }
}