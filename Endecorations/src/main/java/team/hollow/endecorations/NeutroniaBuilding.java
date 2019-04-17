package team.hollow.endecorations;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;

import static team.hollow.neutronia.utils.registry.BlockRegistryBuilder.getInstance;

public class NeutroniaBuilding implements ModInitializer {

    @Override
    public void onInitialize() {
        getInstance("grass_block", Blocks.GRASS_BLOCK).wall();
    }

}