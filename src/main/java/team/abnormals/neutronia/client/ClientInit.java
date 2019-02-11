package team.abnormals.neutronia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import team.abnormals.neutronia.blocks.entity.SignBlockEntity;
import team.abnormals.neutronia.blocks.entity.StoneChestBlockEntity;
import team.abnormals.neutronia.client.entity.render.VillagerEntityRenderer;
import team.abnormals.neutronia.client.renderer.SignBlockEntityRenderer;
import team.abnormals.neutronia.client.renderer.StoneChestBlockEntityRenderer;
import team.abnormals.neutronia.entity.passive.VillagerPlusEntity;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(VillagerPlusEntity.class, (entityRenderDispatcher, context) ->
                new VillagerEntityRenderer(entityRenderDispatcher, context.getResourceManager()));
        BlockEntityRendererRegistry.INSTANCE.register(StoneChestBlockEntity.class, new StoneChestBlockEntityRenderer());
        BlockEntityRendererRegistry.INSTANCE.register(SignBlockEntity.class, new SignBlockEntityRenderer());
    }
}