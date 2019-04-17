package team.hollow.villages_and_villagers;

import net.fabricmc.api.ModInitializer;
import team.hollow.neutronia.init.NBlocks;

import static team.hollow.neutronia.utils.registry.BlockRegistryBuilder.getInstance;

public class NeutroniaBuilding implements ModInitializer {

    @Override
    public void onInitialize() {
        getInstance("", NBlocks.SMOOTH_OBSIDIAN).wall();
    }

}