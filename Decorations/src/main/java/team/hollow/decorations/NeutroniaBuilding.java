package team.hollow.decorations;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import team.hollow.neutronia.utils.registry.BlockRegistryBuilder;

public class NeutroniaBuilding implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistryBuilder.getInstance("coarse_dirt", Blocks.COARSE_DIRT).wall();
    }

}