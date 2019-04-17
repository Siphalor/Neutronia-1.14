package team.hollow.questing_villagers;

import net.fabricmc.api.ModInitializer;
import team.hollow.neutronia.init.NBlocks;

import static team.hollow.neutronia.utils.registry.BlockRegistryBuilder.getInstance;

public class NeutroniaBuilding implements ModInitializer {

    @Override
    public void onInitialize() {
        getInstance("dead_acan_coral", NBlocks.DEAD_ACAN_CORAL).wall();
    }

}