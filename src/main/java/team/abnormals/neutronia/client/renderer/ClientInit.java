package team.abnormals.neutronia.client.renderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.client.render.BlockEntityRendererRegistry;
import team.abnormals.neutronia.blocks.entity.SignBlockEntity;
import team.abnormals.neutronia.blocks.entity.TileStoneChest;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(TileStoneChest.class, new TileEntityStoneChestRenderer());
        BlockEntityRendererRegistry.INSTANCE.register(SignBlockEntity.class, new SignBlockEntityRenderer());
    }
}