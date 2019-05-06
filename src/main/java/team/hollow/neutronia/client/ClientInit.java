package team.hollow.neutronia.client;

import de.siphalor.tweed.client.TweedClothBridge;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import team.hollow.module_api.ModuleManager;
import team.hollow.neutronia.blocks.entity.CustomChestBlockEntity;
import team.hollow.neutronia.client.entity.render.BlackBearEntityRenderer;
import team.hollow.neutronia.client.entity.render.BrownBearEntityRenderer;
import team.hollow.neutronia.client.entity.render.SocialVillagerRenderer;
import team.hollow.neutronia.client.entity.render.VillagerEntityRenderer;
import team.hollow.neutronia.client.renderer.StoneChestBlockEntityRenderer;
import team.hollow.neutronia.entity.SocialVillager;
import team.hollow.neutronia.entity.passive.BlackBearEntity;
import team.hollow.neutronia.entity.passive.GrizzlyBearEntity;
import team.hollow.neutronia.entity.passive.VillagerPlusEntity;

public class ClientInit implements ClientModInitializer {
    public static TweedClothBridge tweedClothBridge;

    @Override
    public void onInitializeClient() {
        tweedClothBridge = new TweedClothBridge(ModuleManager.configFile);

        BlockEntityRendererRegistry.INSTANCE.register(CustomChestBlockEntity.class, new StoneChestBlockEntityRenderer());

        EntityRendererRegistry.INSTANCE.register(VillagerPlusEntity.class, (manager, context) -> new VillagerEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(GrizzlyBearEntity.class, (manager, context) -> new BrownBearEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(BlackBearEntity.class, (manager, context) -> new BlackBearEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(SocialVillager.class, (manager, context) -> new SocialVillagerRenderer(manager));
        /*EntityRendererRegistry.INSTANCE.register(EntityOlDiggy.class, (manager, context) -> new RenderOlDiggy(manager));
        EntityRendererRegistry.INSTANCE.register(EntityShadowPhantom.class, (manager, context) -> new RenderShadowPhantom(manager));
        EntityRendererRegistry.INSTANCE.register(EntityEnderPhantom.class, (manager, context) -> new RenderEnderPhantom(manager));
        EntityRendererRegistry.INSTANCE.register(EntityBloodPhantom.class, (manager, context) -> new RenderBloodPhantom(manager));
        EntityRendererRegistry.INSTANCE.register(ArcticWolfEntity.class, (manager, context) -> new ArcticWolfEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(EntityJungleFrog.class, (manager, context) -> new JungleFrogEntityRenderer(manager));
        EntityRendererRegistry.INSTANCE.register(AxolotlEntity.class, (manager, context) -> new AxolotlEntityRenderer(manager));*/
    }
}