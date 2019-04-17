package team.hollow.decorations;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;

import static team.hollow.neutronia.utils.registry.BlockRegistryBuilder.getInstance;

public class NeutroniaBuilding implements ModInitializer {

    @Override
    public void onInitialize() {
        getInstance("coarse_dirt", Blocks.COARSE_DIRT).wall();
    }

}