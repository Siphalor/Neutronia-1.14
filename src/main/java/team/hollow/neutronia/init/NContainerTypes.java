package team.hollow.neutronia.init;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class NContainerTypes implements ModInitializer {

    public static final Identifier SAWMILL_CONTAINER = new Identifier("neutronia", "sawmill_container");

    @Override
    public void onInitialize() {
//        ContainerProviderRegistry.INSTANCE.registerFactory(SAWMILL_CONTAINER, (syncId, identifier, player, buf) -> new SawmillContainer(syncId, player.inventory));
    }

}
